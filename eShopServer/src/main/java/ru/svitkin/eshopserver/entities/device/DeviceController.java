package ru.svitkin.eshopserver.entities.device;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceInputDto;
import ru.svitkin.eshopserver.entities.device.dtos.DeviceOutputDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping
    public List<DeviceOutputDto> getAll() {
        return deviceService.getAll().stream()
                .map(device -> new ModelMapper().map(device, DeviceOutputDto.class)).toList();
    }

    @GetMapping
    public DeviceOutputDto getOne(@RequestParam int id) {
        Device device = deviceService.getById(id);
        return new ModelMapper().map(device, DeviceOutputDto.class);
    }

    @PostMapping
    public DeviceOutputDto create(@Valid @RequestBody DeviceInputDto deviceInputDto) {
        Device device = deviceService.create(deviceInputDto);
        return new ModelMapper().map(device, DeviceOutputDto.class);
    }
}
