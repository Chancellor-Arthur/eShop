package ru.svitkin.eshopserver.entities.device.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.svitkin.eshopserver.entities.brand.dtos.BrandOutputDto;
import ru.svitkin.eshopserver.entities.deviceinfo.dtos.DeviceInfoInputDto;
import ru.svitkin.eshopserver.entities.type.dtos.TypeOutputDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceOutputDto {
	private int id;

	private String name;

	private int price;

	private int rating;

	private String image;

	private TypeOutputDto type;

	private BrandOutputDto brand;

	private List<DeviceInfoInputDto> devicesInfo;
}
