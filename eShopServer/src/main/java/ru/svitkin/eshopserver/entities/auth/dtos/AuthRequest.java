package ru.svitkin.eshopserver.entities.auth.dtos;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
