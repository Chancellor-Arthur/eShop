package ru.svitkin.eshopserver.exceptions.specific;

import org.springframework.http.HttpStatus;

import ru.svitkin.eshopserver.exceptions.ApplicationException;

public class UnauthorizedException extends ApplicationException {
	public UnauthorizedException(String message) {
		super(HttpStatus.UNAUTHORIZED, message);
	}
}
