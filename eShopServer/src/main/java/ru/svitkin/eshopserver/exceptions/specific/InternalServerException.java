package ru.svitkin.eshopserver.exceptions.specific;

import org.springframework.http.HttpStatus;

import ru.svitkin.eshopserver.exceptions.ApplicationException;

public class InternalServerException extends ApplicationException {
	public InternalServerException(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
}
