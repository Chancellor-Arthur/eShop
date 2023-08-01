package ru.svitkin.eshopserver.entities.auth.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.svitkin.eshopserver.entities.auth.dtos.UserInputDto;
import ru.svitkin.eshopserver.entities.user.UserService;
import ru.svitkin.eshopserver.exceptions.UnauthorizedException;

@Component
@RequiredArgsConstructor
public class RegistrationValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return UserInputDto.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @Nullable Errors errors) {
        UserInputDto userInputDto = (UserInputDto) target;

        if (!userInputDto.getPassword().equals(userInputDto.getConfirmPassword()))
            throw new UnauthorizedException("Пароли не совпадают");

        if (userService.findByUsername(userInputDto.getUsername()).isPresent())
            throw new UnauthorizedException("Пользователь с указанным именем уже существует");
    }
}
