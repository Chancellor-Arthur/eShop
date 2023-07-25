package ru.svitkin.eshopserver.entities.brand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.svitkin.eshopserver.entities.brand.dtos.BrandInputDto;
import ru.svitkin.eshopserver.entities.brand.dtos.BrandOutputDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public List<BrandOutputDto> getAll() {
        return brandService.getAll().stream().map(brand -> new ModelMapper().map(brand, BrandOutputDto.class)).toList();
    }

    @PostMapping
    public BrandOutputDto create(@Valid @RequestBody BrandInputDto brandInputDto) {
        Brand brand = brandService.create(brandInputDto);
        return new ModelMapper().map(brand, BrandOutputDto.class);
    }
}
