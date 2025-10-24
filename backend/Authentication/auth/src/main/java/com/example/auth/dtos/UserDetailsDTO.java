package com.example.auth.dtos;

import java.util.UUID;

public class UserDetailsDTO {
    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String address;
    private Integer age;
    private String role;

    public UserDetailsDTO() {}

    public UserDetailsDTO(UUID id, String name, String username, String password, String email, String address, Integer age, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.age = age;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public Integer getAge() {
        return age;
    }

    public String getRole() {
        return role;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
