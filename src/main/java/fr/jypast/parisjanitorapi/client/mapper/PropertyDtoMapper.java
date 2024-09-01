package fr.jypast.parisjanitorapi.client.mapper;

import fr.jypast.parisjanitorapi.client.dto.property.PropertyCreationRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;

import java.util.UUID;

public interface PropertyDtoMapper {

    static PropertyDto toDto(Property domain) {
        return new PropertyDto(
                domain.getId(),
                domain.getAddress(),
                domain.getDescription(),
                domain.isAvailable(),
                domain.getOwnerId(),
                domain.isValidated()
                );
    }

    static Property creationRequestToDomain(PropertyCreationRequest request) {
        return Property.builder()
                .id(UUID.randomUUID())
                .address(request.adress().trim())
                .description(request.description().trim())
                .ownerId(request.ownerId())
                .build();
    }

}

