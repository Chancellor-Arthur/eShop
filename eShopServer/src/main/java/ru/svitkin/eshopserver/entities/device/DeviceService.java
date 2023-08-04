package ru.svitkin.eshopserver.entities.device;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.brand.Brand;
import ru.svitkin.eshopserver.entities.brand.BrandService;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceInputDto;
import ru.svitkin.eshopserver.entities.deviceinfo.DeviceInfo;
import ru.svitkin.eshopserver.entities.deviceinfo.DeviceInfoService;
import ru.svitkin.eshopserver.entities.file.File;
import ru.svitkin.eshopserver.entities.file.FileService;
import ru.svitkin.eshopserver.entities.type.Type;
import ru.svitkin.eshopserver.entities.type.TypeService;
import ru.svitkin.eshopserver.exceptions.specific.NotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceService {
	private final DeviceRepository deviceRepository;
	private final BrandService brandService;
	private final TypeService typeService;
	private final DeviceInfoService deviceInfoService;
	private final FileService fileService;

	public Device create(DeviceInputDto deviceInputDto) {
		Brand brand = brandService.getById(deviceInputDto.getBrandId());
		Type type = typeService.getById(deviceInputDto.getTypeId());
		File file = fileService.find(deviceInputDto.getFileId()).orElse(null);

		Device device = new Device(deviceInputDto.getName(), deviceInputDto.getPrice(), file, type, brand);
		Device savedDevice = deviceRepository.save(device);
		List<DeviceInfo> deviceInfos = deviceInfoService.bulkCreate(savedDevice, deviceInputDto.getDeviceInfos());

		savedDevice.setDeviceInfos(deviceInfos);

		return savedDevice;
	}

	public List<Device> getAll() {
		return deviceRepository.findAll();
	}

	public Device getById(int id) {
		return deviceRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Устройство с id: %d не найден", id)));
	}
}
