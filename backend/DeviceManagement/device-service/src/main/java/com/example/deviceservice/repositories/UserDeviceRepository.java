package com.example.deviceservice.repositories;

import com.example.deviceservice.entities.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, UUID> {
    Optional<UserDevice> findByUser_IdAndDevice_Id(UUID userId, UUID deviceId);
    List<UserDevice> findAllByUser_Id(UUID userId);
}
