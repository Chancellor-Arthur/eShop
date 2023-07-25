package ru.svitkin.eshopserver.entities.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthInputDto;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthOutputDto;
import ru.svitkin.eshopserver.entities.auth.dtos.UserInputDto;
import ru.svitkin.eshopserver.entities.role.Role;
import ru.svitkin.eshopserver.entities.user.User;
import ru.svitkin.eshopserver.entities.user.UserService;
import ru.svitkin.eshopserver.entities.user.dtos.UserOutputDto;
import ru.svitkin.eshopserver.exceptions.UnauthorizedException;
import ru.svitkin.eshopserver.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthValidator authValidator;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthOutputDto signIn(AuthInputDto authInputDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authInputDto.getUsername(), authInputDto.getPassword()));
        } catch (BadCredentialsException exception) {
            throw new UnauthorizedException("Неправильный логин или пароль");
        }

        UserDetails userDetails = userService.loadUserByUsername(authInputDto.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new AuthOutputDto(token);
    }

    public UserOutputDto signUp(UserInputDto userInputDto) {
        authValidator.validate(userInputDto, null);

        User user = userService.create(userInputDto);
        return new UserOutputDto(user.getUsername(), user.getEmail(), user.getRoles().stream().map(Role::getName).toList());
    }
}
