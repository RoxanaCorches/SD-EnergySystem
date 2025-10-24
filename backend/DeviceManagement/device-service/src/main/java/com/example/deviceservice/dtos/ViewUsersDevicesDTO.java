package com.example.deviceservice.dtos;

import java.util.List;
import java.util.UUID;

public class ViewUsersDevicesDTO {
    private UUID id;
    private String name;
    private List<DeviceDTO> devices;

    public ViewUsersDevicesDTO(UUID id, String name, List<DeviceDTO> devices) {
        this.id = id;
        this.name = name;
        this.devices = devices;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<DeviceDTO> getDevices() {
        return devices;
    }
    public void setDevices(List<DeviceDTO> devices) {
        this.devices = devices;
    }
}
