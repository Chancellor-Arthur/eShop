package ru.svitkin.eshopserver.entities.file;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.svitkin.eshopserver.entities.file.dtos.FileOutputDto;
import ru.svitkin.eshopserver.exceptions.dtos.BadRequestExceptionPayload;
import ru.svitkin.eshopserver.exceptions.dtos.DefaultExceptionPayload;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/files")
@Tag(name = "Файлы", description = "Взаимодействие с файлами")
@SecurityRequirement(name = "JWT")
@ApiResponse(responseCode = "401", content = {
		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
@ApiResponse(responseCode = "403", content = {
		@Content(schema = @Schema(implementation = DefaultExceptionPayload.class)) })
public class FileController {
	private final FileService fileService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Сохранение файла", description = "Позволяет сохранить файл в серверном хранилище")
	@ApiResponse(responseCode = "201", content = {
			@Content(array = @ArraySchema(schema = @Schema(implementation = FileOutputDto.class))) })
	@ApiResponse(responseCode = "400", content = {
			@Content(schema = @Schema(implementation = BadRequestExceptionPayload.class)) })
	public FileOutputDto upload(@RequestParam MultipartFile file) {
		File createdFile = fileService.save(file);
		return new ModelMapper().map(createdFile, FileOutputDto.class);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Удаление файла", description = "Позволяет удалить файл из серверного хранилища")
	@ApiResponse(responseCode = "204")
	@ApiResponse(responseCode = "400", content = {
			@Content(schema = @Schema(implementation = BadRequestExceptionPayload.class)) })
	public void delete(@PathVariable int id) {
		fileService.delete(id);
	}
}
