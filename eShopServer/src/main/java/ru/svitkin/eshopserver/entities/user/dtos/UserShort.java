package ru.svitkin.eshopserver.entities.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserShort {
    private int id;
    private String username;
    private String email;
}
