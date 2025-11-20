package com.example.monitoringservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "hourly_consumption", uniqueConstraints = @UniqueConstraint(columnNames = {"device_id", "hour_start"}))
@Data
public class HourlyEnergyConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    private UUID deviceId;

    @Column(name = "hour_start", nullable = false)
    private Instant hourStart;

    @Column(name = "total_kwh", nullable = false)
    private double totalKwh;

    public HourlyEnergyConsumption() {}

    public HourlyEnergyConsumption(Long id, UUID deviceId, Instant hourStart, double totalKwh) {
        this.id = id;
        this.deviceId = deviceId;
        this.hourStart = hourStart;
        this.totalKwh = totalKwh;
    }

    public Long getId() {
        return id;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public Instant getHourStart() {
        return hourStart;
    }

    public double getTotalKwh() {
        return totalKwh;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public void setHourStart(Instant hourStart) {
        this.hourStart = hourStart;
    }

    public void setTotalKwh(double totalKwh) {
        this.totalKwh = totalKwh;
    }
}