package ru.svitkin.eshopserver.exceptions.specific;

import org.springframework.http.HttpStatus;

import ru.svitkin.eshopserver.exceptions.global.ApplicationException;

public class NotFoundException extends ApplicationException {
	public NotFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}
