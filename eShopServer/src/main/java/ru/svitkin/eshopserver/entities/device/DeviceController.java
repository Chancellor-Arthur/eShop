package ru.svitkin.eshopserver.entities.device;

import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceInputDto;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceOutputDto;
import ru.svitkin.eshopserver.entities.device.dtos.DevicesPage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
@Tag(name = "Устройства", description = "Взаимодействие с каталогом устройств")
//@SecurityRequirement(name = "JWT")
//@ApiResponse(responseCode = "400", content = {
//		@Content(schema = @Schema(implementation = BadRequestExceptionPayload.class)) })
//@ApiResponse(responseCode = "401", content = {
//		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
//@ApiResponse(responseCode = "403", content = {
//		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
public class DeviceController {
	private final DeviceService deviceService;
	private final DeviceConverter deviceConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Создание устройства", description = "Позволяет создать устройство")
	@ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = DeviceOutputDto.class)) })
	public DeviceOutputDto create(@Valid @RequestBody DeviceInputDto deviceInputDto) {
		Device device = deviceService.create(deviceInputDto);
		return deviceConverter.convert(device);
	}

	@GetMapping
	@Operation(summary = "Получение списка устройств", description = "Позволяет получить список устройств")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = DevicesPage.class)) })
	public Page<DeviceOutputDto> getAll(@RequestParam Optional<Integer> brandId, @RequestParam Optional<Integer> typeId,
			@ParameterObject Pageable pageable) {
		return deviceService.getAll(brandId, typeId, pageable.getPageNumber(), pageable.getPageSize())
				.map(deviceConverter::convert);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Получение устройства по уникальному идентификатору", description = "Позволяет получить запрашиваемое устройство")
	@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = DeviceOutputDto.class)) })
	public DeviceOutputDto getOne(@PathVariable int id) {
		Device device = deviceService.getById(id);
		return deviceConverter.convert(device);
	}
}
