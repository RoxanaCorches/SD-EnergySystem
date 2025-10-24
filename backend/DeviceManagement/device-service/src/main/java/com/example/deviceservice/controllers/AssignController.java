package com.example.deviceservice.controllers;

import com.example.deviceservice.dtos.ViewUsersDevicesDTO;
import com.example.deviceservice.services.UserDeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sync/assign")
public class AssignController {
    private final UserDeviceService userDeviceService;

    public AssignController(UserDeviceService userDeviceService) {
        this.userDeviceService = userDeviceService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getDevicesForUser(@PathVariable UUID userId) {
        System.out.println("Fetching devices for user " + userId);
        try {
            var result = userDeviceService.getDevicesForUser(userId);
            System.out.println("Found devices: " + result.size());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/allUsersDevices")
    public ResponseEntity<List<ViewUsersDevicesDTO>> getAllUsersDevices() {
        List<ViewUsersDevicesDTO> result = userDeviceService.viewDevicesForUsers() ;
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{userId}/{deviceId}")
    public ResponseEntity<String> assignDevice(@PathVariable UUID userId, @PathVariable UUID deviceId) {
        String result = userDeviceService.assignDeviceToUser(userId, deviceId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{userId}/{deviceId}")
    public ResponseEntity<String> unassignDevice(@PathVariable UUID userId, @PathVariable UUID deviceId) {
        boolean removed = userDeviceService.unassignDeviceFromUser(userId, deviceId);
        if (removed) {
            return ResponseEntity.ok("Unassigned device " + deviceId + " from user " + userId);
        } else {
            return ResponseEntity.badRequest().body("No relation found between user and device");
        }
    }
}
