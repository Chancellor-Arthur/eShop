package ru.svitkin.eshopserver.entities.device;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.svitkin.eshopserver.entities.brand.Brand;
import ru.svitkin.eshopserver.entities.brand.BrandService;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceInputDto;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceOutputDto;
import ru.svitkin.eshopserver.entities.deviceinfo.DeviceInfo;
import ru.svitkin.eshopserver.entities.deviceinfo.DeviceInfoService;
import ru.svitkin.eshopserver.entities.type.Type;
import ru.svitkin.eshopserver.entities.type.TypeService;
import ru.svitkin.eshopserver.exceptions.specific.NotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final BrandService brandService;
    private final TypeService typeService;
    private final DeviceInfoService deviceInfoService;

    public Device create(DeviceInputDto deviceInputDto) {
        Brand brand = brandService.getById(deviceInputDto.getBrandId());
        Type type = typeService.getById(deviceInputDto.getTypeId());
        Device device = new Device(deviceInputDto.getName(),
                deviceInputDto.getPrice(),
                deviceInputDto.getImage(),
                type,
                brand);
        Device savedDevice = deviceRepository.save(device);
        List<DeviceInfo> deviceInfos = deviceInfoService.bulkCreate(savedDevice, deviceInputDto.getDeviceInfos());

        savedDevice.setDeviceInfos(deviceInfos);

        return savedDevice;
    }

    public List<DeviceOutputDto> getAll() {
        return deviceRepository.findAll()
                .stream()
                .map(device -> new ModelMapper().map(device, DeviceOutputDto.class))
                .toList();
    }

    public Device getById(int id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Устройство с id: %d не найден", id)));
    }
}
