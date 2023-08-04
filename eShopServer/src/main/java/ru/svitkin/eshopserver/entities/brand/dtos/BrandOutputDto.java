package ru.svitkin.eshopserver.entities.brand.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandOutputDto {
	private int id;

	private String name;
}
