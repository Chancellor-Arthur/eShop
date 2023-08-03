package ru.svitkin.eshopserver.entities.auth.validators;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.contracts.Validator;
import ru.svitkin.eshopserver.entities.auth.dtos.UserInputDto;
import ru.svitkin.eshopserver.entities.user.UserService;
import ru.svitkin.eshopserver.exceptions.specific.UnauthorizedException;

@Component
@RequiredArgsConstructor
public class RegistrationValidator implements Validator {
	private final UserService userService;

	@Override
	public void validate(Object target) {
		UserInputDto userInputDto = (UserInputDto) target;

		if (!userInputDto.getPassword().equals(userInputDto.getConfirmPassword()))
			throw new UnauthorizedException("Пароли не совпадают");

		if (userService.findByUsername(userInputDto.getUsername()).isPresent())
			throw new UnauthorizedException("Пользователь с указанным именем уже существует");
	}
}
