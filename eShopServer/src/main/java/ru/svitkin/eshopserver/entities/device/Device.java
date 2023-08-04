package ru.svitkin.eshopserver.entities.device;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.config.db.BaseEntity;
import ru.svitkin.eshopserver.entities.brand.Brand;
import ru.svitkin.eshopserver.entities.deviceinfo.DeviceInfo;
import ru.svitkin.eshopserver.entities.file.File;
import ru.svitkin.eshopserver.entities.rating.Rating;
import ru.svitkin.eshopserver.entities.type.Type;

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

	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private Type type;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@OneToMany(mappedBy = "device", fetch = FetchType.EAGER)
	private List<Rating> ratings;

	@OneToMany(mappedBy = "device", fetch = FetchType.EAGER)
	private List<DeviceInfo> deviceInfos;

	@Transient
	private int rating;

	public Device(String name, int price, File file, Type type, Brand brand) {
		this.name = name;
		this.price = price;
		this.file = file;
		this.type = type;
		this.brand = brand;
	}

	public int getRating() {
		if (ratings == null)
			return 0;
		return ratings.stream().mapToInt(Rating::getRate).sum();
	}
}
