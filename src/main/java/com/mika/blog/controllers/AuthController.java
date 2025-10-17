package com.mika.blog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mika.blog.domain.dtos.AuthResponse;
import com.mika.blog.domain.dtos.LoginRequest;
import com.mika.blog.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    // 🔹 Register new user
    // @PostMapping("/register")
    // public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    // if (userRepository.findByEmail(request.getEmail()).isPresent()) {
    // return ResponseEntity.badRequest().body("Email already exists");
    // }

    // User user = User.builder()
    // .name(request.getName())
    // .email(request.getEmail())
    // .password(passwordEncoder.encode(request.getPassword()))
    // .build();

    // userRepository.save(user);

    // String token = authenticationService.generateToken(user);
    // return ResponseEntity.ok(new AuthResponse(token));
    // }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(loginRequest.getEmail(),
                loginRequest.getPassword());
        String tokenValue = authenticationService.generateToken(userDetails);

        AuthResponse response = AuthResponse.builder().token(tokenValue).expiresIn(86400).build();
        return ResponseEntity.ok(response);

    }
}
