package com.example.deviceservice.dtos;

import java.util.Objects;
import java.util.UUID;

public class DeviceDTO {
    private UUID id;
    private String name;
    private String maxConsumption;
    private String description;


    public DeviceDTO() {}

    public DeviceDTO(UUID id, String name, String maxConsumption, String description) {
        this.id = id;
        this.name = name;
        this.maxConsumption = maxConsumption;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMaxConsumption() {
        return maxConsumption;
    }

    public String getDescription() {
        return description;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxConsumption(String maxConsumption) {
        this.maxConsumption = maxConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDTO that = (DeviceDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(maxConsumption, that.maxConsumption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxConsumption);
    }
}
