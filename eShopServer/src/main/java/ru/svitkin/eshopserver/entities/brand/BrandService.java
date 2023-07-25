package ru.svitkin.eshopserver.entities.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.svitkin.eshopserver.entities.brand.dtos.BrandInputDto;
import ru.svitkin.eshopserver.exceptions.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Brand getById(int id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Бренд с id: %d не найден", id)));
    }

    public Brand create(BrandInputDto brandInputDto) {
        Brand brand = new Brand(brandInputDto.getName());
        return brandRepository.save(brand);
    }
}
