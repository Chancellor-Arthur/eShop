package ru.svitkin.eshopserver.entities.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.svitkin.eshopserver.entities.brand.dtos.BrandDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public List<BrandDto> getAll() {
        return brandService.getAll();
    }

    @PostMapping
    public Brand create(@RequestBody BrandDto brandDto) {
        return brandService.create(brandDto);
    }
}
