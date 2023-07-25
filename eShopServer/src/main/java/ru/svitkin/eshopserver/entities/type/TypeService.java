package ru.svitkin.eshopserver.entities.type;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svitkin.eshopserver.entities.type.dtos.TypeInputDto;
import ru.svitkin.eshopserver.exceptions.NotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    public Type getById(int id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Тип устройства с id: %d не найден", id)));
    }

    public Type create(TypeInputDto typeInputDto) {
        Type type = new Type(typeInputDto.getName());
        return typeRepository.save(type);
    }
}
