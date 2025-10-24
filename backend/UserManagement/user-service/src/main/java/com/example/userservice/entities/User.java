package com.example.userservice.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    public User() {}

    public User(UUID id, String name, String username, String password, String address, String email, int age, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public User(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public User(String name, String username, String password, String address, String email, Integer age, Role role) {
            this.name = name;
            this.username = username;
            this.password = password;
            this.address = address;
            this.email = email;
            this.age = age;
            this.role = role;
    }

    public String getName() {
        return name;
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

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public Role getRole() {
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


    /*
    public void setPassword(String password) {
        if (password == null) {
            this.password = null;
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.password = encoder.encode(password);
        }
    }

     */
    public void setPassword(String password) {
        this.password = password;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
