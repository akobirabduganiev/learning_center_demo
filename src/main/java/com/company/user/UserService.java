package com.company.user;

import com.company.exceptions.ItemAlreadyExistsException;
import com.company.exceptions.ItemNotFoundException;
import com.company.registration.sms.ConfirmationSms;
import com.company.registration.sms.ConfirmationSmsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG =
            "user with phone %s not found";

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final ConfirmationSmsService confirmationSmsService;

    @Override
    public UserDetails loadUserByUsername(String phone)
            throws UsernameNotFoundException {
        return userRepository.findByPhone(phone)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, phone)
                        ));
    }

    public String signUpUser(User user) {
        var optional = userRepository.findByPhone(user.getPhone());

        if (optional.isPresent())
            throw new ItemAlreadyExistsException("phone number already exists!");

        var encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        var sms = getRandomNumberString();

        var smsOptional = confirmationSmsService.getSms(sms);
        smsOptional.ifPresent(confirmationSmsService::delete);

        var confirmationSms = new ConfirmationSms(
                sms,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(2),
                user
        );
        confirmationSmsService.confirmationSms(confirmationSms);

        // TODO: an SMS code must be sent to the phone number

        return sms;
    }

    public void enableUser(String phone) {
        userRepository.enableUser(phone);
    }

    public User updateUserDetail(String firstName, String lastName,
                                 String phone) {
        var user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new ItemNotFoundException("user not found!"));
        userRepository.updateUserDetail(firstName, lastName, phone);

        return user;
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public User getUserByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> new ItemNotFoundException("user not found"));
    }
    private String getRandomNumberString() {

        var rnd = new Random();
        int number = rnd.nextInt(99999);

        return String.format("%05d", number);
    }
}
