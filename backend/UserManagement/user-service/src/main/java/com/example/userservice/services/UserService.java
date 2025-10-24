package com.example.userservice.services;

import com.example.userservice.dtos.*;
import com.example.userservice.dtos.builders.UserBuilder;
import com.example.userservice.entities.Role;
import com.example.userservice.entities.User;
import com.example.userservice.handlers.exceptions.model.ResourceNotFoundException;
import com.example.userservice.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${device.service.url}")
    private String deviceServiceUrl;

    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public List<UserGetDTO> findUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserBuilder::toUserGetDTO)
                .collect(Collectors.toList());
    }

    public UserDetailsDTO findUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toUserDetailsDTO(userOptional.get());
    }

    public UUID insert(UserDetailsDTO userDTO) {
        User user = UserBuilder.toEntity(userDTO);
        user = userRepository.save(user);
        LOGGER.debug("User with id {} was inserted in db", user.getId());
        try {
            String url = deviceServiceUrl + "/sync/users";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            UserSyncDTO syncDTO = new UserSyncDTO(user.getId(), user.getName());
            HttpEntity<UserSyncDTO> request = new HttpEntity<>(syncDTO, headers);
            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                LOGGER.info("Utilizatorul cu id {} a fost adaugat cu success", user.getId());
            } else {
                LOGGER.warn("Utilizatorul cu id {} nu a putut fi adaugat in baza de date user din device-service", user.getId());
            }
        } catch (Exception e) {
            LOGGER.error("Sincronizarea utilizatorului {} cu serviciul device-service a eșuat: {}", user.getId(), e.getMessage());
            userRepository.delete(user);
            throw new RuntimeException("Sincronizarea utilizatorului cu serviciul device-service a eșuat. Inserarea a fost anulată (rollback).", e);
        }
        return user.getId();
    }

    public UUID update(UserUpdateDTO userDTO) {
        UUID userId = userDTO.getId();
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id " + userId + " not found"));
        if (userDTO.getName() != null) {
            existingUser.setName(userDTO.getName());
        }
        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getPassword() != null) {
            existingUser.setPassword(userDTO.getPassword());
        }

        User updatedUser = userRepository.save(existingUser);
        LOGGER.debug("User with id {} was updated in db", updatedUser.getId());

        try {
            String url = deviceServiceUrl + "/sync/users/" + updatedUser.getId();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            UserSyncDTO syncDTO = new UserSyncDTO(updatedUser.getId(), updatedUser.getName());
            HttpEntity<UserSyncDTO> request = new HttpEntity<>(syncDTO, headers);

            restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

            LOGGER.info("User with id {} synchronized update with device-service", updatedUser.getId());
        } catch (Exception e) {
            LOGGER.error("Failed to sync update for user {} with device-service: {}", updatedUser.getId(), e.getMessage());
            throw new RuntimeException("Failed to sync update with device-service.", e);
        }

        return updatedUser.getId();
    }


    public UUID delete(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            LOGGER.warn("User with id {} not found in DB", id);
            throw new EntityNotFoundException("User-ul cu id-ul " + id + " nu există în baza de date.");
        }
        userRepository.delete(userOptional.get());
        LOGGER.info("User with id {} was deleted from DB", id);

        try {
            String url = deviceServiceUrl + "/sync/users/" + id;
            restTemplate.delete(url);
            LOGGER.info("User with id {} deleted also in device-service", id);
        } catch (Exception e) {
            LOGGER.error("Failed to delete user {} from device-service: {}", id, e.getMessage());
            throw new RuntimeException("User-ul a fost șters local, dar nu a putut fi șters din device-service", e);
        }
        return id;
    }
}
