package ru.svitkin.eshopserver.entities.user.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {
	private String username;

	private String email;

	private List<String> roles;
}
