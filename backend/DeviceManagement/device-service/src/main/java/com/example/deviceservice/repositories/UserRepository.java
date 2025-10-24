package com.example.deviceservice.repositories;

import com.example.deviceservice.entities.UserD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserD, UUID> {
    Optional<UserD> findByName(String name);
}
