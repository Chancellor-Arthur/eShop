package ru.svitkin.eshopserver.entities.device;

import java.util.List;

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
import lombok.extern.slf4j.Slf4j;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceInputDto;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceOutputDto;
import ru.svitkin.eshopserver.exceptions.dtos.BadRequestExceptionPayload;
import ru.svitkin.eshopserver.exceptions.dtos.DefaultExceptionPayload;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/devices")
@Tag(name = "Устройства", description = "Взаимодействие с каталогом устройств")
@SecurityRequirement(name = "JWT")
@ApiResponse(responseCode = "401", content = {
		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
@ApiResponse(responseCode = "403", content = {
		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
public class DeviceController {
	private final DeviceService deviceService;
	private final DeviceConverter deviceConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Создание устройства", description = "Позволяет создать устройство")
	@ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DeviceOutputDto.class)) })
	@ApiResponse(responseCode = "400", content = {
			@Content(schema = @Schema(implementation = BadRequestExceptionPayload.class)) })
	public DeviceOutputDto create(@Valid @RequestBody DeviceInputDto deviceInputDto) {
		Device device = deviceService.create(deviceInputDto);
		return deviceConverter.convert(device);
	}

	@GetMapping
	@Operation(summary = "Получение списка устройств", description = "Позволяет получить список устройств")
	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = DeviceOutputDto.class))) })
	public List<DeviceOutputDto> getAll() {
		return deviceService.getAll().stream().map(deviceConverter::convert).toList();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Получение устройства по уникальному идентификатору", description = "Позволяет получить запрашиваемое устройство")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = DeviceOutputDto.class)) })
	@ApiResponse(responseCode = "400", content = {
			@Content(schema = @Schema(implementation = BadRequestExceptionPayload.class)) })
	public DeviceOutputDto getOne(@PathVariable int id) {
		Device device = deviceService.getById(id);
		return deviceConverter.convert(device);
	}
}
