package ru.svitkin.eshopserver.entities.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthInputDto;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthOutputDto;
import ru.svitkin.eshopserver.entities.auth.dtos.UserInputDto;
import ru.svitkin.eshopserver.entities.auth.validators.AuthValidator;
import ru.svitkin.eshopserver.entities.auth.validators.RegistrationValidator;
import ru.svitkin.eshopserver.entities.role.Role;
import ru.svitkin.eshopserver.entities.user.User;
import ru.svitkin.eshopserver.entities.user.UserService;
import ru.svitkin.eshopserver.entities.user.dtos.UserOutputDto;
import ru.svitkin.eshopserver.utils.JwtTokenUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
	private final UserService userService;
	private final RegistrationValidator registrationValidator;
	private final AuthValidator authValidator;
	private final JwtTokenUtils jwtTokenUtils;

	public AuthOutputDto signIn(AuthInputDto authInputDto) {
		authValidator.validate(authInputDto);

		UserDetails userDetails = userService.loadUserByUsername(authInputDto.getUsername());
		String token = jwtTokenUtils.generateToken(userDetails);

		return new AuthOutputDto(token);
	}

	public UserOutputDto signUp(UserInputDto userInputDto) {
		registrationValidator.validate(userInputDto);

		User user = userService.create(userInputDto);
		return new UserOutputDto(user.getUsername(), user.getEmail(),
				user.getRoles().stream().map(Role::getName).toList());
	}
}
