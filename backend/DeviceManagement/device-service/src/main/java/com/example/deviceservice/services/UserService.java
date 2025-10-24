package com.example.deviceservice.services;

import com.example.deviceservice.entities.UserD;
import com.example.deviceservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserD> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void syncCreate(UserD user) {
        System.out.println("Received user from user-service: " + user.getId() + " / " + user.getName());

        Optional<UserD> existing = userRepository.findById(user.getId());
        if (existing.isPresent()) {
            UserD existingUser = existing.get();
            existingUser.setName(user.getName());
            userRepository.save(existingUser);
            System.out.println("Updated existing user " + existingUser.getId());
        } else {
            UserD newUser = new UserD();
            newUser.setId(user.getId());
            newUser.setName(user.getName());
            userRepository.save(newUser);
            System.out.println("Created new user " + newUser.getId());
        }
    }

    public void syncUpdate(UUID id, UserD user) {
        Optional<UserD> existing = userRepository.findById(id);

        if (existing.isPresent()) {
            UserD existingUser = existing.get();
            existingUser.setName(user.getName());
            userRepository.save(existingUser);
            System.out.println("User updated: " + id);
        } else {
            System.out.println("User not found for update: " + id + ", creating new one...");
            UserD newUser = new UserD();
            newUser.setId(id);
            newUser.setName(user.getName());
            userRepository.save(newUser);
            System.out.println("Created new user " + newUser.getId());
        }
    }

    public void syncDelete(UUID id) {
        userRepository.deleteById(id);
        System.out.println("Deleted user: " + id);
    }
}
