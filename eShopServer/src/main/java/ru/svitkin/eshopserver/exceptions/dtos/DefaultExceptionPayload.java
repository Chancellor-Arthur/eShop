package ru.svitkin.eshopserver.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultExceptionPayload {
    public String timestamp;

    public int status;

    public String error;

    public String message;

    public String path;
}
