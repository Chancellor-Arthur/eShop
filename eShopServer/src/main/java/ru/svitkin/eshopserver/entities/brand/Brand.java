package ru.svitkin.eshopserver.entities.brand;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.entities.type.Type;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
public class Brand {
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

    @ManyToMany
    @JoinTable
            (
                    name = "types_brands",
                    joinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id")
            )
    private List<Type> types;

    public Brand(String name) {
        this.name = name;
    }
}
