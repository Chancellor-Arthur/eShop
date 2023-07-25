package ru.svitkin.eshopserver.entities.deviceInfo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.config.BaseEntity;
import ru.svitkin.eshopserver.entities.device.Device;

@Entity
@Table(name = "devices_info")
@Getter
@Setter
@NoArgsConstructor
public class DeviceInfo extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    public DeviceInfo(String title, String description, Device device) {
        this.title = title;
        this.description = description;
        this.device = device;
    }
}
