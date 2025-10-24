package com.example.userservice.controllers;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.dtos.UserDetailsDTO;
import com.example.userservice.dtos.UserGetDTO;
import com.example.userservice.dtos.UserUpdateDTO;
import com.example.userservice.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserGetDTO>> getUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @PostMapping
    public ResponseEntity<UserDetailsDTO> create(@Valid @RequestBody UserDetailsDTO user) {
        UUID id = userService.insert(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        UserDetailsDTO createdUser = userService.findUserById(id);
        return ResponseEntity.created(location).body(createdUser);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        userUpdateDTO.setId(id);
        UUID updatedId = userService.update(userUpdateDTO);
        return ResponseEntity.ok("User with id " + updatedId + " was successfully updated.");
    }
/*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

 */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        try {
            userService.delete(id);
            String message = "User-ul cu id-ul " + id + " a fost șters cu succes.";
            return ResponseEntity.ok(message);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("A apărut o eroare neașteptată la ștergerea user-ului.");
        }
    }

}
