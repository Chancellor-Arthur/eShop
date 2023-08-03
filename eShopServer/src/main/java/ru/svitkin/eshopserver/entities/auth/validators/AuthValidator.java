package ru.svitkin.eshopserver.entities.auth.validators;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.contracts.Validator;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthInputDto;
import ru.svitkin.eshopserver.exceptions.specific.UnauthorizedException;

@Component
@RequiredArgsConstructor
public class AuthValidator implements Validator {
	private final AuthenticationManager authenticationManager;

	@Override
	public void validate(Object target) {
		AuthInputDto authInputDto = (AuthInputDto) target;

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authInputDto.getUsername(), authInputDto.getPassword()));
		} catch (BadCredentialsException exception) {
			throw new UnauthorizedException("Неправильный логин или пароль");
		}
	}
}
