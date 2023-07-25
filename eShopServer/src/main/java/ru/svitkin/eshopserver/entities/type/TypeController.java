package ru.svitkin.eshopserver.entities.type;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.svitkin.eshopserver.entities.type.dtos.TypeInputDto;
import ru.svitkin.eshopserver.entities.type.dtos.TypeOutputDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    @GetMapping
    public List<TypeOutputDto> getAll() {
        return typeService.getAll().stream().map(type -> new ModelMapper().map(type, TypeOutputDto.class)).toList();
    }

    @PostMapping
    public TypeOutputDto create(@Valid @RequestBody TypeInputDto typeInputDto) {
        Type type = typeService.create(typeInputDto);
        return new ModelMapper().map(type, TypeOutputDto.class);
    }
}
