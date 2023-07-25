package ru.svitkin.eshopserver.entities.deviceInfo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfoInputDto {
    @NotEmpty(message = "Заголовок не может быть пустым")
    @Size(min = 2, max = 100, message = "Заголовок должно быть от 2 до 100 символов длиной")
    private String title;

    @NotEmpty(message = "Описание не может быть пустым")
    @Size(min = 5, max = 100, message = "Описание должно быть от 5 символов длиной")
    private String description;
}
