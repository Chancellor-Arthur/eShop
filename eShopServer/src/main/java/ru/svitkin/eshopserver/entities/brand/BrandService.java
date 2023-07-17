package ru.svitkin.eshopserver.entities.brand;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.svitkin.eshopserver.entities.brand.dtos.BrandDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public List<BrandDto> getAll() {
        return brandRepository.findAll().stream().map(brand -> new ModelMapper().map(brand, BrandDto.class)).toList();
    }

    public Brand create(BrandDto brandDto) {
        Brand brand = new Brand(brandDto.getName());
        return brandRepository.save(brand);
    }
}
