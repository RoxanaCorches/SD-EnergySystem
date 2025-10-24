package com.example.deviceservice.services;

import com.example.deviceservice.dtos.DeviceDTO;
import com.example.deviceservice.dtos.ViewUsersDevicesDTO;
import com.example.deviceservice.entities.Device;
import com.example.deviceservice.entities.UserD;
import com.example.deviceservice.entities.UserDevice;
import com.example.deviceservice.repositories.DeviceRepository;
import com.example.deviceservice.repositories.UserDeviceRepository;
import com.example.deviceservice.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserDeviceService {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final UserDeviceRepository userDeviceRepository;


    public UserDeviceService(UserRepository userRepository, DeviceRepository deviceRepository, UserDeviceRepository userDeviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.userDeviceRepository = userDeviceRepository;
    }

    @Transactional(readOnly = true)
    public List<DeviceDTO> getDevicesForUser(UUID userId) {
        Optional<UserD> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }

        List<UserDevice> userDevices = userDeviceRepository.findAllByUser_Id(userId);

        return userDevices.stream()
                .map(ud -> {
                    Device d = ud.getDevice();
                    return new DeviceDTO(
                            d.getId(),
                            d.getName(),
                            d.getMaxConsumption(),
                            d.getDescription()
                    );
                })
                .toList();
    }

        public String assignDeviceToUser(UUID userId, UUID deviceId) {
            Optional<UserD> user = userRepository.findById(userId);
            Optional<Device> device = deviceRepository.findById(deviceId);

            if (user.isEmpty()) {
                return "User with id " + userId + " not found";
            }
            if (device.isEmpty()) {
                return "Device with id " + deviceId + " not found";
            }

            Optional<UserDevice> existingLink =
                    userDeviceRepository.findByUser_IdAndDevice_Id(userId, deviceId);

            if (existingLink.isPresent()) {
                return "This device is already assigned to this user";
            }

            UserDevice link = new UserDevice();
            link.setUser(user.get());
            link.setDevice(device.get());
            userDeviceRepository.save(link);

            return "Device " + deviceId + " assigned to user " + userId;
        }

        public boolean unassignDeviceFromUser(UUID userId, UUID deviceId) {
            Optional<UserDevice> relation =
                    userDeviceRepository.findByUser_IdAndDevice_Id(userId, deviceId);
            if (relation.isPresent()) {
                userDeviceRepository.delete(relation.get());
                return true;
            }
            return false;
        }

    @Transactional(readOnly = true)
    public List<ViewUsersDevicesDTO> viewDevicesForUsers() {
        return userRepository.findAll().stream().map(user -> {
            List<DeviceDTO> devices = user.getUserDevices().stream()
                    .map(ud -> {
                        Device d = ud.getDevice();
                        return new DeviceDTO(d.getId(), d.getName(), d.getMaxConsumption(), d.getDescription());
                    })
                    .collect(Collectors.toList());

            return new ViewUsersDevicesDTO(user.getId(), user.getName(), devices);
        }).collect(Collectors.toList());
    }

}

