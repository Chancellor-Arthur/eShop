package ru.svitkin.eshopserver.entities.rating;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.config.db.BaseEntity;
import ru.svitkin.eshopserver.entities.device.Device;
import ru.svitkin.eshopserver.entities.user.User;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@NoArgsConstructor
public class Rating extends BaseEntity {
    @Column(name = "rate")
    private int rate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    public Rating(int rate, User user, Device device) {
        this.rate = rate;
        this.user = user;
        this.device = device;
    }
}
