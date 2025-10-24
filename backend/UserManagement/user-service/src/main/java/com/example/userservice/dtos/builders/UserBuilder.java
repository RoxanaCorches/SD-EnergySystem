package com.example.userservice.dtos.builders;

import com.example.userservice.dtos.UserDTO;
import com.example.userservice.dtos.UserDetailsDTO;
import com.example.userservice.dtos.UserGetDTO;
import com.example.userservice.dtos.UserUpdateDTO;
import com.example.userservice.entities.Role;
import com.example.userservice.entities.User;

import java.util.UUID;

public class UserBuilder {

    private UserBuilder() {
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getAddress(), user.getRole(), user.getAge());
    }

    public static UserDetailsDTO toUserDetailsDTO(User user) {
        return new UserDetailsDTO(user.getId(),user.getName(), user.getUsername(), user.getPassword(), user.getAddress(), user.getEmail(), user.getAge(), user.getRole());
    }

    public static UserGetDTO toUserGetDTO(User user) {
        return new UserGetDTO(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getAddress(), user.getRole(), user.getAge());
    }

    public static UserUpdateDTO toUserUpdateDTO(User user){
        return new UserUpdateDTO(user.getId(), user.getName(), user.getPassword(), user.getUsername());
    }

    public static User toEntity(UserDetailsDTO userDetailsDTO) {
        return new User(userDetailsDTO.getName(),
                userDetailsDTO.getUsername(),
                userDetailsDTO.getPassword(),
                userDetailsDTO.getAddress(),
                userDetailsDTO.getEmail(),
                userDetailsDTO.getAge(),
                userDetailsDTO.getRole());
    }

}
