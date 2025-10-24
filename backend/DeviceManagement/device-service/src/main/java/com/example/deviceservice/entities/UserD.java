package com.example.deviceservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserD {
    private static final long serialVersionUID = 1L;

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserDevice> userDevices = new HashSet<>();

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserDevices(Set<UserDevice> userDevices) {
        this.userDevices = userDevices;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<UserDevice> getUserDevices() {
        return userDevices;
    }
}

