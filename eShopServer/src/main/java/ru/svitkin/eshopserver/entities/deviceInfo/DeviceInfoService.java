package ru.svitkin.eshopserver.entities.deviceInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svitkin.eshopserver.entities.device.Device;
import ru.svitkin.eshopserver.entities.deviceInfo.dtos.DeviceInfoInputDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceInfoService {
    private final DeviceInfoRepository deviceInfoRepository;

    public DeviceInfo create(Device device, DeviceInfoInputDto deviceInfoInputDto) {
        DeviceInfo deviceInfo = new DeviceInfo(deviceInfoInputDto.getTitle(), deviceInfoInputDto.getDescription(), device);
        return deviceInfoRepository.save(deviceInfo);
    }

    public List<DeviceInfo> bulkCreate(Device device, List<DeviceInfoInputDto> deviceInfoInputDtos) {
        List<DeviceInfo> devicesInfo = deviceInfoInputDtos
                .stream()
                .map(deviceInfoInputDto -> new DeviceInfo(deviceInfoInputDto.getTitle(), deviceInfoInputDto.getDescription(), device))
                .toList();
        return deviceInfoRepository.saveAll(devicesInfo);
    }
}
