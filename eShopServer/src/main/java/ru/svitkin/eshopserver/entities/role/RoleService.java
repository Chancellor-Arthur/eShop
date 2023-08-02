package ru.svitkin.eshopserver.entities.role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.exceptions.specific.NotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
	private final RoleRepository roleRepository;

	public Role getUserRole() {
		return roleRepository.findByName(Roles.ADMIN)
				.orElseThrow(() -> new NotFoundException(String.format("Роль '%s' не найдена", Roles.ADMIN)));
	}
}
