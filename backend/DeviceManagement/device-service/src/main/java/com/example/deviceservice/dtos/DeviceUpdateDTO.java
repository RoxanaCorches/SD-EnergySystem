package com.example.deviceservice.dtos;

import java.util.UUID;

public class DeviceUpdateDTO {
    private UUID deviceId;
    private String name;
    private String maxConsumption;

    public DeviceUpdateDTO(UUID deviceId, String name, String maxConsumption) {
        this.deviceId = deviceId;
        this.name = name;
        this.maxConsumption = maxConsumption;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return name;
    }

    public String getMaxConsumption() {
        return maxConsumption;
    }

    public void setDeviceName(String name) {
        this.name = name;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public void setMaxConsumption(String maxConsumption) {
        this.maxConsumption = maxConsumption;
    }
}
