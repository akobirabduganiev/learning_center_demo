package com.company.authorization;

import com.company.dto.AuthDTO;
import com.company.dto.UserDTO;
import com.company.user.User;
import com.company.user.UserRepository;
import com.company.user.UserService;
import com.company.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public UserDTO login(AuthDTO dto) {
        var encoder = new BCryptPasswordEncoder();

        var profile = userRepository
                .findByPhone(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Login or Password not valid."));

        if (!encoder.matches(dto.getPassword(), profile.getPassword()))
            throw new RuntimeException("Login or Password not valid");

        var jwt = JwtUtil.createJwt(profile.getId(), profile.getUsername());

        var user = new UserDTO();
        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());
        user.setUsername(profile.getUsername());
        user.setJwt(jwt);

        return user;
    }
}
