package ru.svitkin.eshopserver.entities.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthInputDto;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthOutputDto;
import ru.svitkin.eshopserver.entities.auth.dtos.UserInputDto;
import ru.svitkin.eshopserver.entities.user.dtos.UserOutputDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthOutputDto createAuthToken(@Valid @RequestBody AuthInputDto authInputDto) {
        return authService.signIn(authInputDto);
    }

    @PostMapping("/registration")
    public UserOutputDto createNewUser(@Valid @RequestBody UserInputDto userInputDto) {
        return authService.signUp(userInputDto);
    }
}
