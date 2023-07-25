package ru.svitkin.eshopserver.entities.brand.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandOutputDto {
    private int id;

    private String name;

    private Date createdAt;

    private Date updatedAt;
}
