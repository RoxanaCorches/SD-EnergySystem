package com.example.userservice.repositories;

import com.example.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByName(String name);
    List<User> findByUsername(String username);
    @Query(value = "SELECT p " +
            "FROM User p " +
            "WHERE p.name = :name " +
            "AND p.age >= 60  ")
    Optional<User> findSeniorsByName(@Param("name") String name);

}
