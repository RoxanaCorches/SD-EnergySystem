package com.example.deviceservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_device")
public class UserDevice  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "userDevices"})
    private UserD user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_device", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "userDevices"})
    private Device device;

    public UUID getId() {
        return id;
    }

    public UserD getUser() {
        return user;
    }

    public Device getDevice() {
        return device;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUser(UserD user) {
        this.user = user;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}