package com.example.monitoringservice.dtos;

import lombok.Data;

@Data
public class MeasurementDTO {

    private String timestamp;
    private String deviceId;
    private double measurementValue;

    public MeasurementDTO() {}

    public MeasurementDTO(String timestamp, String deviceId, double measurementValue) {
        this.timestamp = timestamp;
        this.deviceId = deviceId;
        this.measurementValue = measurementValue;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public double getMeasurementValue() {
        return measurementValue;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setMeasurementValue(double measurementValue) {
        this.measurementValue = measurementValue;
    }
}