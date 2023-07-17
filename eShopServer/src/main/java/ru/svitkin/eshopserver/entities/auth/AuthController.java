package ru.svitkin.eshopserver.entities.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthRequestDto;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthResponseDto;
import ru.svitkin.eshopserver.entities.auth.dtos.RegistrationInfoDto;
import ru.svitkin.eshopserver.entities.user.dtos.UserDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDto createAuthToken(@RequestBody AuthRequestDto authRequestDto) {
        return authService.signIn(authRequestDto);
    }

    @PostMapping("/registration")
    public UserDto createNewUser(@RequestBody RegistrationInfoDto registrationInfoDto) {
        return authService.signUp(registrationInfoDto);
    }
}
