package ru.svitkin.eshopserver.entities.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.auth.dtos.UserInputDto;
import ru.svitkin.eshopserver.entities.basket.BasketService;
import ru.svitkin.eshopserver.entities.role.RoleService;
import ru.svitkin.eshopserver.exceptions.specific.NotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	private final UserRepository userRepository;
	private final RoleService roleService;
	private final BasketService basketService;
	private final PasswordEncoder passwordEncoder;

	public User create(UserInputDto userInputDto) {
		User user = new User(userInputDto.getUsername(), passwordEncoder.encode(userInputDto.getPassword()),
				userInputDto.getEmail(), roleService.getUserRole());
		User savedUser = userRepository.save(user);

		basketService.create(savedUser);

		return savedUser;
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getByUsername(username);

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList());
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getByUsername(String username) {
		return findByUsername(username)
				.orElseThrow(() -> new NotFoundException(String.format("Пользователь '%s' не найден", username)));
	}
}
