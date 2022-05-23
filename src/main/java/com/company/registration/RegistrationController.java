package com.company.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody RegistrationRequest request) {

        return ResponseEntity.ok(registrationService.registerTeacher(request));
    }

    @PostMapping("/student")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(registrationService.registerStudent(request));
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<?> confirm(@RequestParam("sms") String sms) {
        return ResponseEntity.ok(registrationService.confirmSms(sms));
    }
}
