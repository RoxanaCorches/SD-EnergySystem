package com.example.deviceservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "devices")
public class Device implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "maxConsumption", nullable = false)
    private String maxConsumption;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserDevice> userDevices;

    //ADAUG UN FIELD de user


    public Device() {}

    public Device(UUID id, String name, String maxConsumption, String description) {
        this.id = id;
        this.name = name;
        this.maxConsumption = maxConsumption;
        this.description = description;
        userDevices = new HashSet<>();

    }

    public Device(String name, String maxConsumption, String description) {
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

    public Set<UserDevice> getUserDevices() {
        return userDevices;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxConsumption(String maxConsumption) {
        this.maxConsumption = maxConsumption;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserDevices(Set<UserDevice> userDevices) {
        this.userDevices = userDevices;
    }
}
