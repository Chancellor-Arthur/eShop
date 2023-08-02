package ru.svitkin.eshopserver.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
public class Validation {
    private final Validator validator;

    public Validator check(Class<?> clazz) {
        if (validator == null)
            throw new IllegalArgumentException("The supplied [Validator] is required and must not be null");

        if (!validator.supports(clazz))
            throw new IllegalArgumentException(
                    String.format("The supplied [%s] must support the validation of [%s] instances",
                            validator.getClass(),
                            clazz));

        return validator;
    }
}
