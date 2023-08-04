package ru.svitkin.eshopserver.entities.device;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ru.svitkin.eshopserver.entities.brand.dtos.BrandOutputDto;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceOutputDto;
import ru.svitkin.eshopserver.entities.deviceinfo.dtos.DeviceInfoInputDto;
import ru.svitkin.eshopserver.entities.file.dtos.FileOutputDto;
import ru.svitkin.eshopserver.entities.type.dtos.TypeOutputDto;

@Component
public class DeviceConverter {
	public DeviceOutputDto convert(Device device) {
		BrandOutputDto brandOutputDto = new ModelMapper().map(device.getBrand(), BrandOutputDto.class);
		TypeOutputDto typeOutputDto = new ModelMapper().map(device.getType(), TypeOutputDto.class);
		FileOutputDto fileOutputDto = new ModelMapper().map(device.getFile(), FileOutputDto.class);
		List<DeviceInfoInputDto> deviceInfoInputDtos = device.getDeviceInfos().stream()
				.map(deviceInfo -> new ModelMapper().map(deviceInfo, DeviceInfoInputDto.class)).toList();

		return new DeviceOutputDto(device.getId(), device.getName(), device.getPrice(), device.getRating(),
				fileOutputDto, typeOutputDto, brandOutputDto, deviceInfoInputDtos);
	}
}
