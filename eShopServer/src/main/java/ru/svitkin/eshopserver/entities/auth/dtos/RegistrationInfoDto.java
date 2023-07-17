package ru.svitkin.eshopserver.entities.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationInfoDto {
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя пользователя должно быть от 2 до 30 символов длиной")
    private String username;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 200, message = "Пароль должен быть от 6 до 200 символов длиной")
    private String password;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 200, message = "Пароль должен быть от 6 до 200 символов длиной")
    private String confirmPassword;

    @NotEmpty(message = "Адрес электронной почты не может быть пустым")
    @Email
    private String email;
}
