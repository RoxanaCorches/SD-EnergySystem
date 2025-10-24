package com.example.userservice.dtos;


import com.example.userservice.dtos.validators.annotation.AgeLimit;
import com.example.userservice.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class UserDetailsDTO {
    private UUID id;

    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "email is required")
    private String email;
    @NotNull(message = "role is required")
    private Role role;
    @NotNull(message = "age is required")
    @AgeLimit(value = 18)
    private Integer age;

    public UserDetailsDTO() {}

    public UserDetailsDTO(UUID id, String name, String username, String password, String address, String email, Integer age, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Integer getAge() {
        return age;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsDTO that = (UserDetailsDTO) o;
        return age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address)&&
                Objects.equals(email, that.email) &&
                Objects.equals(role, that.role) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password, address, email, age, role);
    }
}
