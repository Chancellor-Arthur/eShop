package ru.svitkin.eshopserver.entities.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthRequest;
import ru.svitkin.eshopserver.entities.auth.dtos.AuthResponse;
import ru.svitkin.eshopserver.entities.auth.dtos.RegistrationUser;
import ru.svitkin.eshopserver.entities.user.User;
import ru.svitkin.eshopserver.entities.user.UserService;
import ru.svitkin.eshopserver.entities.user.dtos.UserShort;
import ru.svitkin.eshopserver.exceptions.AppError;
import ru.svitkin.eshopserver.utils.JwtTokenUtils;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        //@TODO: Спрятать в сервис
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException exception) {
            //@TODO: Сделать нормальную обработку исключений
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUser registrationUser) {
        //@TODO: Спрятать в сервис
        if (!registrationUser.getPassword().equals(registrationUser.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }

        if (userService.findByUsername(registrationUser.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.UNAUTHORIZED);
        }

        User user = userService.createUser(registrationUser);
        return ResponseEntity.ok(new UserShort(user.getId(), user.getUsername(), user.getEmail()));
    }

    @GetMapping("/login")
    public String login() {
        return "Arthur";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Arthur";
    }

    @GetMapping("/admin/asd")
    public String temp() {
        return "Arthur";
    }

    @GetMapping("/info")
    public Principal info(Principal principal) {
        return principal;
    }
}
