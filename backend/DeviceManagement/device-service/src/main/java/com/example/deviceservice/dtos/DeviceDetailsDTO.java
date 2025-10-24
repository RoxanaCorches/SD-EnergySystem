package com.example.deviceservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class DeviceDetailsDTO {
    private UUID id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "description is required")
    private String description;
    @NotNull(message = "maxConsumption is required")
    private String maxConsumption;

    public DeviceDetailsDTO() {}

    public DeviceDetailsDTO(String name, String description, String maxConsumption, UUID userId) {
        this.name = name;
        this.description = description;
        this.maxConsumption = maxConsumption;
    }

    public DeviceDetailsDTO(UUID id, String name, String description, String maxConsumption) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxConsumption = maxConsumption;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMaxConsumption() {
        return maxConsumption;
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
        DeviceDetailsDTO that = (DeviceDetailsDTO) o;
        return maxConsumption == that.maxConsumption &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, maxConsumption);
    }
}
