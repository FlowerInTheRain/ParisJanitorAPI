package fr.jypast.parisjanitorapi.client.mapper;

import fr.jypast.parisjanitorapi.client.dto.property.PropertyCreationRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.ConciergerieType;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.ContactSlot;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.PropertyType;

import java.util.UUID;
import java.util.stream.Collectors;

public interface PropertyDtoMapper {

    static PropertyDto toDto(Property domain) {
        return new PropertyDto(
                domain.getId(),
                domain.getAddress(),
                domain.getDescription(),
                domain.isAvailable(),
                domain.getOwnerId(),
                domain.isValidated(),
                domain.getNumberOfRooms(),
                domain.getCapacity(),
                domain.getPropertyType().name(),
                domain.getCountry(),
                domain.getSize(),
                domain.getImageUrls(),
                domain.getContactSlots().stream().map(Enum::name).collect(Collectors.toList()),
                domain.isPrivacyDeclaration(),
                domain.getConciergerieType().name()
        );
    }

    static Property creationRequestToDomain(PropertyCreationRequest request) {
        return Property.builder()
                .id(UUID.randomUUID())
                .address(request.address().trim())
                .description(request.description().trim())
                .numberOfRooms(request.numberOfRooms())
                .capacity(request.capacity())
                .propertyType(PropertyType.valueOf(request.propertyType().toUpperCase()))
                .country(request.country().trim())
                .size(request.size())
                .imageUrls(request.imageUrls())
                .contactSlots(request.contactSlots().stream()
                        .map(slot -> ContactSlot.valueOf(slot.toUpperCase()))
                        .collect(Collectors.toList()))
                .privacyDeclaration(request.privacyDeclaration())
                .conciergerieType(ConciergerieType.valueOf(request.conciergerieType().toUpperCase()))
                .build();
    }

    static Property patchRequestToDomain(PropertyCreationRequest request) {
        return Property.builder()
                .address(request.address().trim())
                .description(request.description().trim())
                .numberOfRooms(request.numberOfRooms())
                .capacity(request.capacity())
                .propertyType(PropertyType.valueOf(request.propertyType().toUpperCase()))
                .country(request.country().trim())
                .size(request.size())
                .contactSlots(request.contactSlots().stream()
                        .map(slot -> ContactSlot.valueOf(slot.toUpperCase()))
                        .collect(Collectors.toList()))
                .privacyDeclaration(request.privacyDeclaration())
                .conciergerieType(ConciergerieType.valueOf(request.conciergerieType().toUpperCase()))
                .build();
    }
}
