package com.example.deviceservice.services;

import com.example.deviceservice.dtos.DeviceDTO;
import com.example.deviceservice.dtos.DeviceDetailsDTO;
import com.example.deviceservice.dtos.builders.DeviceBuilder;
import com.example.deviceservice.entities.Device;
import com.example.deviceservice.handlers.exceptions.model.ResourceNotFoundException;
import com.example.deviceservice.repositories.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceDTO> findDevices() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }

    public DeviceDetailsDTO findDeviceById(UUID id) {
        Optional<Device> prosumerOptional = deviceRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + id);
        }
        return DeviceBuilder.toDeviceDetailsDTO(prosumerOptional.get());
    }

    public UUID insert(DeviceDetailsDTO deviceDTO) {
        Device device = DeviceBuilder.toEntity(deviceDTO);
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in db", device.getId());
        return device.getId();
    }

    public UUID update(DeviceDetailsDTO deviceDTO) {
        UUID deviceId = deviceDTO.getId();
        Device existingDevice = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Device with id " + deviceId + " not found"));
        if (deviceDTO.getName() != null) {
            existingDevice.setName(deviceDTO.getName());
        }
        if (deviceDTO.getMaxConsumption() != null) {
            existingDevice.setMaxConsumption(deviceDTO.getMaxConsumption());
        }
        if (deviceDTO.getDescription() != null) {
            existingDevice.setDescription(deviceDTO.getDescription());
        }

        Device updatedDevice = deviceRepository.save(existingDevice);
        LOGGER.debug("Device with id {} was updated in db", updatedDevice.getId());
        return updatedDevice.getId();
    }

    public void delete(UUID id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (deviceOptional.isEmpty()) {
            throw new EntityNotFoundException("Device-ul cu id-ul " + id + " nu a fost găsit în baza de date.");
        }
        deviceRepository.delete(deviceOptional.get());
        LOGGER.debug("Device-ul cu id {} a fost șters din baza de date", id);
    }
}
