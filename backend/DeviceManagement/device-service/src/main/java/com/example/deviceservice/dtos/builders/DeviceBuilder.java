package com.example.deviceservice.dtos.builders;

import com.example.deviceservice.dtos.DeviceDTO;
import com.example.deviceservice.dtos.DeviceDetailsDTO;
import com.example.deviceservice.dtos.DeviceUpdateDTO;
import com.example.deviceservice.entities.Device;

public class DeviceBuilder {

    private DeviceBuilder() {
    }

    public static DeviceDTO toDeviceDTO(Device device) {
        return new DeviceDTO(device.getId(), device.getName(),device.getMaxConsumption(), device.getDescription());
    }

    public static DeviceDetailsDTO toDeviceDetailsDTO(Device device) {
        return new DeviceDetailsDTO(
                device.getId(),
                device.getName(),
                device.getMaxConsumption(),
                device.getDescription()

        );
    }
    public static DeviceUpdateDTO toDeviceUpdateDTO(Device device) {
        return new DeviceUpdateDTO(
                device.getId(),
                device.getName(),
                device.getMaxConsumption()
        );
    }


    public static Device toEntity(DeviceDetailsDTO deviceDetailsDTO) {
        return new Device(deviceDetailsDTO.getName(),
                deviceDetailsDTO.getMaxConsumption(),
                deviceDetailsDTO.getDescription()
        );
    }
}
