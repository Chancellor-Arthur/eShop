package ru.svitkin.eshopserver.entities.type;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.entities.brand.Brand;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "types")
@Getter
@Setter
@NoArgsConstructor
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(mappedBy = "types")
    private List<Brand> brands;

    public Type(String name) {
        this.name = name;
    }
}
