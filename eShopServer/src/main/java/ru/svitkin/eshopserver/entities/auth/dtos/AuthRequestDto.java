package ru.svitkin.eshopserver.entities.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDto {
    private String username;
    private String password;
}
