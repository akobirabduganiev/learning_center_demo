package com.company.authorization;

import com.company.dto.AuthDTO;
import com.company.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/authorization/")
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody AuthDTO dto) {
        return ResponseEntity.ok(authorizationService.login(dto));
    }
}
