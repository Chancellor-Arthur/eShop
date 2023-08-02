package ru.svitkin.eshopserver.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultExceptionPayload {
	private String timestamp;

	private int status;

	private String error;

	private String message;

	private String path;
}
