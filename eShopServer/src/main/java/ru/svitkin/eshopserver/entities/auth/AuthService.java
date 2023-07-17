package ru.svitkin.eshopserver.entities.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthRequestDto;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthResponseDto;
import ru.svitkin.eshopserver.entities.auth.dtos.RegistrationInfoDto;
import ru.svitkin.eshopserver.entities.user.User;
import ru.svitkin.eshopserver.entities.user.UserService;
import ru.svitkin.eshopserver.entities.user.dtos.UserDto;
import ru.svitkin.eshopserver.exceptions.UnauthorizedException;
import ru.svitkin.eshopserver.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto signIn(AuthRequestDto authRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
        } catch (BadCredentialsException exception) {
            throw new UnauthorizedException("Неправильный логин или пароль");
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequestDto.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new AuthResponseDto(token);
    }

    public UserDto signUp(RegistrationInfoDto registrationInfoDto) {
        if (!registrationInfoDto.getPassword().equals(registrationInfoDto.getConfirmPassword()))
            throw new UnauthorizedException("Пароли не совпадают");


        if (userService.findByUsername(registrationInfoDto.getUsername()).isPresent())
            throw new UnauthorizedException("Пользователь с указанным именем уже существует");

        User user = userService.create(registrationInfoDto);
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
