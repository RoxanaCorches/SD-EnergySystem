package com.example.auth.controllers;

import com.example.auth.dtos.AuthenticationRequest;
import com.example.auth.dtos.AuthenticationResponse;
import com.example.auth.dtos.RegisterRequest;
import com.example.auth.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Extragem token-ul din header (dacă vine în format "Bearer <token>")
        String adminToken = authorizationHeader.replace("Bearer ", "");

        return ResponseEntity.ok(authService.register(request, adminToken));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }


}