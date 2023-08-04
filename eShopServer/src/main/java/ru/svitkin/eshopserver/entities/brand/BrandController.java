package ru.svitkin.eshopserver.entities.brand;

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
import lombok.extern.slf4j.Slf4j;
import ru.svitkin.eshopserver.entities.brand.dtos.BrandInputDto;
import ru.svitkin.eshopserver.entities.brand.dtos.BrandOutputDto;
import ru.svitkin.eshopserver.exceptions.dtos.BadRequestExceptionPayload;
import ru.svitkin.eshopserver.exceptions.dtos.DefaultExceptionPayload;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/brands")
@Tag(name = "Бренды", description = "Взаимодействие с брендами устройств")
@SecurityRequirement(name = "JWT")
@ApiResponse(responseCode = "401", content = {
		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
@ApiResponse(responseCode = "403", content = {
		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
public class BrandController {
	private final BrandService brandService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Создание бренда", description = "Позволяет создать бренд устройства")
	@ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = BrandOutputDto.class)) })
	@ApiResponse(responseCode = "400", content = {
			@Content(schema = @Schema(implementation = BadRequestExceptionPayload.class)) })
	public BrandOutputDto create(@Valid @RequestBody BrandInputDto brandInputDto) {
		Brand brand = brandService.create(brandInputDto);
		return new ModelMapper().map(brand, BrandOutputDto.class);
	}

	@GetMapping
	@Operation(summary = "Получение списка брендов", description = "Позволяет получить список брендов устройств")
	@ApiResponse(responseCode = "200", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = BrandOutputDto.class))) })
	public List<BrandOutputDto> getAll() {
		return brandService.getAll().stream().map(brand -> new ModelMapper().map(brand, BrandOutputDto.class)).toList();
	}
}
