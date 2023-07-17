package ru.svitkin.eshopserver.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CommonException{
    public BadRequestException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
