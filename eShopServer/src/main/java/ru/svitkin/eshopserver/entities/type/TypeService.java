package ru.svitkin.eshopserver.entities.type;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.type.dtos.TypeInputDto;
import ru.svitkin.eshopserver.exceptions.specific.NotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeService {
	private final TypeRepository typeRepository;

	public Type create(TypeInputDto typeInputDto) {
		Type type = new Type(typeInputDto.getName());
		return typeRepository.save(type);
	}

	public List<Type> getAll() {
		return typeRepository.findAll();
	}

	public Type getById(int id) {
		return typeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Тип устройства с id: %d не найден", id)));
	}
}
