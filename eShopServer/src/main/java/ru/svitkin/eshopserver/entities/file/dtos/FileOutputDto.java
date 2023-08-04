package ru.svitkin.eshopserver.entities.file.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileOutputDto {
	private int id;

	private String name;
}
