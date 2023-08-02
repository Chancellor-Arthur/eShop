package ru.svitkin.eshopserver.entities.type.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOutputDto {
	private int id;

	private String name;

	private Date createdAt;

	private Date updatedAt;
}
