package ru.svitkin.eshopserver.entities.auth.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthInputDto;
import ru.svitkin.eshopserver.exceptions.specific.UnauthorizedException;

@Component
@RequiredArgsConstructor
public class AuthValidator implements Validator {
    private final AuthenticationManager authenticationManager;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return AuthInputDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @Nullable Errors errors) {
        AuthInputDto authInputDto = (AuthInputDto) target;

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authInputDto.getUsername(),
                    authInputDto.getPassword()));
        } catch (BadCredentialsException exception) {
            throw new UnauthorizedException("Неправильный логин или пароль");
        }
    }
}
