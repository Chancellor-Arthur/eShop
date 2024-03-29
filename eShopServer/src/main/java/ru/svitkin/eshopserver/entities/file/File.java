package ru.svitkin.eshopserver.entities.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.svitkin.eshopserver.config.db.BaseEntity;
import ru.svitkin.eshopserver.entities.device.Device;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class File extends BaseEntity {
	@Column(name = "path")
	private String path;

	@Column(name = "name")
	private String name;

	@OneToOne(mappedBy = "file")
	private Device device;

	public File(String path, String name) {
		this.path = path;
		this.name = name;
	}
}
