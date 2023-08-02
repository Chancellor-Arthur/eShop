package ru.svitkin.eshopserver.entities.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.svitkin.eshopserver.entities.brand.dtos.BrandInputDto;
import ru.svitkin.eshopserver.exceptions.specific.NotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public Brand create(BrandInputDto brandInputDto) {
        Brand brand = new Brand(brandInputDto.getName());
        return brandRepository.save(brand);
    }

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Brand getById(int id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Бренд устройства с id: %d не найден", id)));
    }
}
