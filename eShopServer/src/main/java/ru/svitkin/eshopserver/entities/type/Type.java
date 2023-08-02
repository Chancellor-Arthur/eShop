package ru.svitkin.eshopserver.entities.type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.config.db.BaseEntity;
import ru.svitkin.eshopserver.entities.brand.Brand;

import java.util.List;

@Entity
@Table(name = "types")
@Getter
@Setter
@NoArgsConstructor
public class Type extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "types")
    private List<Brand> brands;

    public Type(String name) {
        this.name = name;
    }
}
