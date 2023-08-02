package ru.svitkin.eshopserver.entities.deviceinfo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.svitkin.eshopserver.entities.device.Device;
import ru.svitkin.eshopserver.entities.deviceinfo.dtos.DeviceInfoInputDto;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceInfoService {
	private final DeviceInfoRepository deviceInfoRepository;

	public List<DeviceInfo> bulkCreate(Device device, List<DeviceInfoInputDto> deviceInfoInputDtos) {
		List<DeviceInfo> devicesInfo = deviceInfoInputDtos.stream()
				.map(deviceInfoInputDto -> new DeviceInfo(deviceInfoInputDto.getTitle(),
						deviceInfoInputDto.getDescription(), device))
				.toList();

		return deviceInfoRepository.saveAll(devicesInfo);
	}

	public DeviceInfo create(Device device, DeviceInfoInputDto deviceInfoInputDto) {
		DeviceInfo deviceInfo = new DeviceInfo(deviceInfoInputDto.getTitle(), deviceInfoInputDto.getDescription(),
				device);

		return deviceInfoRepository.save(deviceInfo);
	}
}
