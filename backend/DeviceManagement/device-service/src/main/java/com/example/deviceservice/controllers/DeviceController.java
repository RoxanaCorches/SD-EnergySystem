package com.example.deviceservice.controllers;

import com.example.deviceservice.dtos.DeviceDTO;
import com.example.deviceservice.dtos.DeviceDetailsDTO;
import com.example.deviceservice.dtos.DeviceUpdateDTO;
import com.example.deviceservice.services.DeviceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
@Validated
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> getDevice() {
        return ResponseEntity.ok(deviceService.findDevices());
    }

    @PostMapping
    public ResponseEntity<DeviceDetailsDTO> create(@Valid @RequestBody DeviceDetailsDTO device) {
        UUID id = deviceService.insert(device);
        DeviceDetailsDTO createdDevice = deviceService.findDeviceById(id); // obținem obiectul complet
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(createdDevice);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DeviceDetailsDTO> getDevice(@PathVariable UUID id) {
        return ResponseEntity.ok(deviceService.findDeviceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDevice(@PathVariable UUID id, @Valid @RequestBody DeviceDetailsDTO deviceUpdateDTO) {
        deviceUpdateDTO.setId(id);
        UUID updatedId = deviceService.update(deviceUpdateDTO);
        return ResponseEntity.ok("Device with id " + updatedId + " was successfully updated.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable UUID id) {
        try {
            deviceService.delete(id);
            String message = "Device-ul cu id-ul " + id + " a fost șters cu succes.";
            return ResponseEntity.ok(message);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("A apărut o eroare la ștergerea device-ului.");
        }
    }

}
