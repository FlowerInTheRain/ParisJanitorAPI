package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.server.entity.PropertyEntity;

public interface PropertyEntityMapper {

    static Property toDomain(PropertyEntity entity) {
        return Property.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .description(entity.getDescription())
                .isAvailable(entity.isAvailable())
                .ownerId(entity.getOwnerId())
                .isValidated(entity.isValidated())
                .numberOfRooms(entity.getNumberOfRooms())
                .capacity(entity.getCapacity())
                .propertyType(entity.getPropertyType())
                .country(entity.getCountry())
                .size(entity.getSize())
                .contactSlots(entity.getContactSlots())
                .privacyDeclaration(entity.isPrivacyDeclaration())
                .build();
    }

    static PropertyEntity fromDomain(Property domain) {
        return PropertyEntity.builder()
                .id(domain.getId())
                .address(domain.getAddress())
                .description(domain.getDescription())
                .isAvailable(domain.isAvailable())
                .ownerId(domain.getOwnerId())
                .isValidated(domain.isValidated())
                .numberOfRooms(domain.getNumberOfRooms())
                .capacity(domain.getCapacity())
                .propertyType(domain.getPropertyType())
                .country(domain.getCountry())
                .size(domain.getSize())
                .contactSlots(domain.getContactSlots())
                .privacyDeclaration(domain.isPrivacyDeclaration())
                .build();
    }
}
