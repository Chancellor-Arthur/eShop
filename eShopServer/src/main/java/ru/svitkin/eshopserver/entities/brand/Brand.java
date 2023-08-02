package ru.svitkin.eshopserver.entities.brand;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.config.db.BaseEntity;
import ru.svitkin.eshopserver.entities.type.Type;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
public class Brand extends BaseEntity {
	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(name = "types_brands", joinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id"))
	private List<Type> types;

	public Brand(String name) {
		this.name = name;
	}
}
