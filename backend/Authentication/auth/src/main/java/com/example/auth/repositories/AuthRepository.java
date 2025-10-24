package com.example.auth.repositories;

import com.example.auth.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<Auth, UUID> {
    @Query("SELECT a FROM Auth a WHERE a.username = :username")
    Optional<Auth> findByUsername(String username);
}
