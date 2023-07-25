package ru.svitkin.eshopserver.entities.role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.config.BaseEntity;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
