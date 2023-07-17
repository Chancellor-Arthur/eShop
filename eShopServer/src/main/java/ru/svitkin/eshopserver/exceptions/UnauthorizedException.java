package ru.svitkin.eshopserver.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CommonException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
