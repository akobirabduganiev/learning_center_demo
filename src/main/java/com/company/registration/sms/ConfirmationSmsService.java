package com.company.registration.sms;

import com.company.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationSmsService {
    private final ConfirmationSmsRepository confirmationSmsRepository;

    public void confirmationSms(ConfirmationSms sms) {
        confirmationSmsRepository.save(sms);
    }

    public Optional<ConfirmationSms> getSms(String sms) {
        return confirmationSmsRepository.findBySms(sms);
    }

    public void setConfirmedAt(String sms) {
        confirmationSmsRepository.updateConfirmedAt(LocalDateTime.now(), sms);
    }

    public void delete(ConfirmationSms sms) {
        confirmationSmsRepository.delete(sms);
    }

}
