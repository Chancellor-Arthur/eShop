package ru.svitkin.eshopserver.entities.brand.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrandDto {
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов длиной")
    private String name;
}
