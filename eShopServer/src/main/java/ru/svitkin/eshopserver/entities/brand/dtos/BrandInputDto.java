package ru.svitkin.eshopserver.entities.brand.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandInputDto {
	@NotBlank(message = "Название не может быть пустым")
	@Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов длиной")
	private String name;
}
