package ru.svitkin.eshopserver.entities.basket;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.config.db.BaseEntity;
import ru.svitkin.eshopserver.entities.device.Device;
import ru.svitkin.eshopserver.entities.user.User;

import java.util.List;

@Entity
@Table(name = "baskets")
@Getter
@Setter
@NoArgsConstructor
public class Basket extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable
            (
                    name = "baskets_devices",
                    joinColumns = @JoinColumn(name = "basket_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "device_id", referencedColumnName = "id")
            )
    private List<Device> devices;

    public Basket(User user, List<Device> devices) {
        this.user = user;
        this.devices = devices;
    }
}
