package com.example.auth.dtos;

import com.example.auth.entities.Role;

import java.util.UUID;

public class AuthDTO {
    private UUID id;
    private String username;
    private String password;
    private Role role;

    public AuthDTO() {}

    public AuthDTO(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public AuthDTO(UUID id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}