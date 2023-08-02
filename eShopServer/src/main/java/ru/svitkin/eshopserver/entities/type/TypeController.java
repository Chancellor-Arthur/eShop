package ru.svitkin.eshopserver.entities.type;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.type.dtos.TypeInputDto;
import ru.svitkin.eshopserver.entities.type.dtos.TypeOutputDto;
import ru.svitkin.eshopserver.exceptions.dtos.BadRequestExceptionPayload;
import ru.svitkin.eshopserver.exceptions.dtos.DefaultExceptionPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/types")
@Tag(name = "Типы", description = "Взаимодействие с типами устройств")
@SecurityRequirement(name = "JWT")
@ApiResponse(responseCode = "401", content = {
		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
@ApiResponse(responseCode = "403", content = {
		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })

public class TypeController {
	private final TypeService typeService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Создание типа", description = "Позволяет создать тип устройства")
	@ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = TypeOutputDto.class)) })
	@ApiResponse(responseCode = "400", content = {
			@Content(schema = @Schema(implementation = BadRequestExceptionPayload.class)) })
	public TypeOutputDto create(@Valid @RequestBody TypeInputDto typeInputDto) {
		Type type = typeService.create(typeInputDto);
		return new ModelMapper().map(type, TypeOutputDto.class);
	}

	@GetMapping
	@Operation(summary = "Получение списка типов", description = "Позволяет получить список типов устройств")
	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = TypeOutputDto.class))) })
	public List<TypeOutputDto> getAll() {
		return typeService.getAll().stream().map(type -> new ModelMapper().map(type, TypeOutputDto.class)).toList();
	}
}
