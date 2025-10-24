package com.example.auth.dtos;

public class RegisterRequest {

    private String name;
    private String username;
    private String password;
    private String email;
    private String address;
    private Integer age;


    public RegisterRequest() {}

    public RegisterRequest(String name, String username, String password, String email, String address, Integer age) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
