package ru.svitkin.eshopserver.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestExceptionPayload extends DefaultExceptionPayload {
    public List<Error> errors;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        List<String> codes;

        ArrayList<Object> arguments;

        private String defaultMessage;

        private String objectName;

        private String field;

        private String rejectedValue;

        private boolean bindingFailure;

        private String code;
    }
}
