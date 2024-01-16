package com.enigma.wmb.controller;

import com.enigma.wmb.model.AuthRequest;
import com.enigma.wmb.model.UserResponse;
import com.enigma.wmb.model.WebResponse;
import com.enigma.wmb.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        log.info(request.getUsername());
        UserResponse userResponse = authService.register(request);
        WebResponse<UserResponse> response = WebResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully create new user")
                .data(userResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest request) {
        UserResponse userResponse = authService.registerAdmin(request);
        WebResponse<UserResponse> response = WebResponse.<UserResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully create new user")
                .data(userResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        log.info(request.getUsername());
        String token = authService.login(request);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfully login")
                .data(token)
                .build();
        return ResponseEntity.ok(response);
    }

}
