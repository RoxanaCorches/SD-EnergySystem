package com.example.deviceservice.controllers;

import com.example.deviceservice.entities.UserD;
import com.example.deviceservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sync/users")
public class SyncUserController {

    private final UserService userService;

    @Autowired
    public SyncUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserD>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserD user) {
        userService.syncCreate(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody UserD user) {
        userService.syncUpdate(id, user);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.syncDelete(id);
        return ResponseEntity.ok().build();
    }
}
