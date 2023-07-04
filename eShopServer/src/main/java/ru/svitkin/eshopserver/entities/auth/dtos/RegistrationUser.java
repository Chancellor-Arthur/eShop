package ru.svitkin.eshopserver.entities.auth.dtos;

import lombok.Data;

@Data
public class RegistrationUser {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
