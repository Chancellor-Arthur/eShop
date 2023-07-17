package ru.svitkin.eshopserver.entities.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.svitkin.eshopserver.entities.auth.dtos.RegistrationInfoDto;
import ru.svitkin.eshopserver.entities.role.RoleService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
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

    public User create(RegistrationInfoDto registrationInfoDto) {
        User user = new User
                (
                        registrationInfoDto.getUsername(),
                        passwordEncoder.encode(registrationInfoDto.getPassword()),
                        registrationInfoDto.getEmail(),
                        roleService.getUserRole()
                );

        return userRepository.save(user);
    }
}
