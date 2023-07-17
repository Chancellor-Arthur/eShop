package ru.svitkin.eshopserver.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CommonException extends ResponseStatusException {
    public CommonException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
