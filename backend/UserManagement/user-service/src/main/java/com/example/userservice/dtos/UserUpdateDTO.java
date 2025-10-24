package com.example.userservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class UserUpdateDTO {

    private UUID id;

    private String name;
    private String username;
    private String password;

    public UserUpdateDTO(UUID id, String name, String password, String username) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

