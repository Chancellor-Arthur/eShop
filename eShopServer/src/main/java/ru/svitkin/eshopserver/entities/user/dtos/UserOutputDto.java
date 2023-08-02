package ru.svitkin.eshopserver.entities.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {
    private String username;

    private String email;

    private List<String> roles;
}
