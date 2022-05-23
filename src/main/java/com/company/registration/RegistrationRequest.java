package com.company.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
}
