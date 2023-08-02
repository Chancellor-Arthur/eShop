package ru.svitkin.eshopserver.config.db;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	protected Instant createdAt;

	@LastModifiedDate
	@Column(name = "updated_at")
	protected Instant updatedAt;
}
