package ru.svitkin.eshopserver.entities.device;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;

@Component
public class DeviceSpecifications {
	public Specification<Device> withBrandAndType(Optional<Integer> brandId, Optional<Integer> typeId) {
		return (device, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			brandId.ifPresent(id -> predicates.add(criteriaBuilder.equal(device.get("brand").get("id"), id)));

			typeId.ifPresent(id -> predicates.add(criteriaBuilder.equal(device.get("type").get("id"), id)));

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
