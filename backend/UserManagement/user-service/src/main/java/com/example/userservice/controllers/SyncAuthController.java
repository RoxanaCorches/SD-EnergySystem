package com.example.userservice.controllers;

import com.example.userservice.dtos.UserDetailsDTO;
import com.example.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/sync/auth")
public class SyncAuthController {
    private final UserService userService;
    public SyncAuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UUID> createUserFromAuth(@RequestBody UserDetailsDTO userDetailsDTO) {
        UUID userId = userService.insert(userDetailsDTO);
        return ResponseEntity.ok(userId);
    }
}
