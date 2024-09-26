package fr.jypast.parisjanitorapi.client.mapper;

import fr.jypast.parisjanitorapi.client.dto.property.PropertyCreationRequest;
import fr.jypast.parisjanitorapi.client.dto.property.PropertyDto;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.*;

import java.util.UUID;
import java.util.stream.Collectors;

public interface PropertyDtoMapper {

    static PropertyDto toDto(Property domain) {
        return new PropertyDto(
                domain.getId(),
                domain.getAdress(),
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
                domain.getConciergerieType().name(),
                domain.getPricePerNight(),
                domain.getCity(),
                domain.getAccommodationType().name()
        );
    }

    static Property creationRequestToDomain(PropertyCreationRequest request) {
        return Property.builder()
                .id(UUID.randomUUID())
                .adress(request.adress().trim())
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
                .pricePerNight(request.pricePerNight())
                .city(request.city().trim())
                .accommodationType(AccommodationType.valueOf(request.accommodationType().toUpperCase()))
                .build();
    }

    static Property patchRequestToDomain(PropertyCreationRequest request) {
        return Property.builder()
                .adress(request.adress().trim())
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
                .city(request.city().trim())
                .pricePerNight(request.pricePerNight())
                .accommodationType(AccommodationType.valueOf(request.accommodationType().toUpperCase()))
                .build();
    }
}
