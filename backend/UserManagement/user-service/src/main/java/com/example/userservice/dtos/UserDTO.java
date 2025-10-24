package com.example.userservice.dtos;

import com.example.userservice.entities.Role;

import java.util.Objects;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String address;
    private Role role;
    private int age;

    public UserDTO() {}

    public UserDTO(UUID id, String name, String username, String password, String email, String address, Role role, int age) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Role getRole() {
        return role;
    }

    public int getAge() {
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO that = (UserDTO) o;
        return age == that.age && Objects.equals(name, that.name);
    }
    @Override public int hashCode() { return Objects.hash(name, age); }
}
