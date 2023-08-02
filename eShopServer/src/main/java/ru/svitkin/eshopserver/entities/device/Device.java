package ru.svitkin.eshopserver.entities.device;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.config.db.BaseEntity;
import ru.svitkin.eshopserver.entities.brand.Brand;
import ru.svitkin.eshopserver.entities.deviceinfo.DeviceInfo;
import ru.svitkin.eshopserver.entities.rating.Rating;
import ru.svitkin.eshopserver.entities.type.Type;

import java.util.List;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
public class Device extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "device")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "device")
    private List<DeviceInfo> deviceInfos;

    @Transient
    private int rating;

    public Device(String name, int price, String image, Type type, Brand brand) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.type = type;
        this.brand = brand;
    }

    public int getRating() {
        return ratings.stream().mapToInt(Rating::getRate).sum();
    }
}
