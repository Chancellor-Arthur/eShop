package ru.svitkin.eshopserver.entities.role;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

/* Пока не используем - не работает
    @Converter(autoApply = true)
    public class RoleConverter implements AttributeConverter<RoleEnum, String> {

        @Override
        public String convertToDatabaseColumn(RoleEnum role) {
            if (role == null) return null;
            return role.getAuthority();
        }

        @Override
        public RoleEnum convertToEntityAttribute(String role) {
            if (role == null) return null;

            return Stream.of(RoleEnum.values())
                    .filter(roleEnum -> roleEnum.getAuthority().equals(role))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
*/