package ru.svitkin.eshopserver.entities.device.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.svitkin.eshopserver.entities.deviceinfo.dtos.DeviceInfoInputDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInputDto {
    @NotBlank(message = "Название не может быть пустым")
    @Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов длиной")
    private String name;

    @Min(value = 0, message = "Цена должна быть неотрицательной")
    private int price;

    private String image;

    private int typeId;

    private int brandId;

    private List<DeviceInfoInputDto> deviceInfos;
}
