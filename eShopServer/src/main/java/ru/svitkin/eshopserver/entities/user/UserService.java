package ru.svitkin.eshopserver.entities.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.svitkin.eshopserver.entities.auth.dtos.UserInputDto;
import ru.svitkin.eshopserver.entities.basket.BasketService;
import ru.svitkin.eshopserver.entities.role.RoleService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BasketService basketService;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", username)));

        return new org.springframework.security.core.userdetails.User
                (
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
                );
    }

    public User create(UserInputDto userInputDto) {
        User user = new User
                (
                        userInputDto.getUsername(),
                        passwordEncoder.encode(userInputDto.getPassword()),
                        userInputDto.getEmail(),
                        roleService.getUserRole()
                );

        User savedUser = userRepository.save(user);
        basketService.create(savedUser);
        return savedUser;
    }
}
