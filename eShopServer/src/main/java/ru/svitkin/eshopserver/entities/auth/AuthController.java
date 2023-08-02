package ru.svitkin.eshopserver.entities.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthInputDto;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthOutputDto;
import ru.svitkin.eshopserver.entities.auth.dtos.UserInputDto;
import ru.svitkin.eshopserver.entities.user.dtos.UserOutputDto;
import ru.svitkin.eshopserver.exceptions.dtos.BadRequestExceptionPayload;
import ru.svitkin.eshopserver.exceptions.dtos.DefaultExceptionPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Авторизация/Регистрация", description = "Позволяет авторизоваться/зарегистрироваться пользователю в системе")
@ApiResponse(responseCode = "400", content = {
		@Content(schema = @Schema(implementation = BadRequestExceptionPayload.class)) })
public class AuthController {
	private final AuthService authService;

	@PostMapping("/login")
	@Operation(summary = "Авторизация пользователя", description = "Позволяет пользователю авторизоваться в системе")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AuthOutputDto.class)) })
	@ApiResponse(responseCode = "401", content = {
			@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
	public AuthOutputDto createAuthToken(@Valid @RequestBody AuthInputDto authInputDto) {
		return authService.signIn(authInputDto);
	}

	@PostMapping("/registration")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Регистрация пользователя", description = "Позволяет пользователю зарегистрироваться в системе")
	@ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = AuthOutputDto.class)) })
	public UserOutputDto createNewUser(@Valid @RequestBody UserInputDto userInputDto) {
		return authService.signUp(userInputDto);
	}
}
