package ru.svitkin.eshopserver.entities.file;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.role.Roles;
import ru.svitkin.eshopserver.exceptions.specific.InternalServerException;
import ru.svitkin.eshopserver.utils.FileUtils;

@Service
@RequiredArgsConstructor
public class FileService {
	private final FileUtils fileUtils;
	private final FileRepository fileRepository;
	@Value("${storage.location}")
	private String storageLocation;

	public File save(MultipartFile file) {
		String path = UUID.randomUUID() + fileUtils.getExtension(file.getOriginalFilename());

		try {
			Files.copy(file.getInputStream(), Paths.get(storageLocation).resolve(path));
		} catch (Exception exception) {
			throw new InternalServerException(exception.getMessage());
		}

		return fileRepository.save(new File(path, file.getOriginalFilename()));
	}

	@Secured(Roles.ADMIN)
	public void delete(int id) {
		Optional<File> file = find(id);
		if (file.isEmpty())
			return;

		try {
			Files.delete(Paths.get(storageLocation).resolve(file.get().getPath()));
		} catch (Exception exception) {
			throw new InternalServerException(exception.getMessage());
		}

		fileRepository.delete(file.get());
	}

	public Optional<File> find(int id) {
		return fileRepository.findById(id);
	}
}
