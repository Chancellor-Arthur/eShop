package ru.svitkin.eshopserver.exceptions.specific;

import org.springframework.http.HttpStatus;
import ru.svitkin.eshopserver.exceptions.global.ApplicationException;

public class BadRequestException extends ApplicationException {
    public BadRequestException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
