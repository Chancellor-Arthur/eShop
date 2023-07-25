package ru.svitkin.eshopserver.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    /*
        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exception) {
            return new ResponseEntity<>(exception.getDetailMessageArguments(), HttpStatus.BAD_REQUEST);
        }
    */
}
