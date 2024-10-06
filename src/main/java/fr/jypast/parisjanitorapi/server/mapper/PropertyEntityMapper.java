package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.server.entity.PropertyEntity;

public interface PropertyEntityMapper {

    static Property toDomain(PropertyEntity entity) {
        return Property.builder()
                .id(entity.getId())
                .adress(entity.getAdress())
                .propertyName(entity.getPropertyName())
                .description(entity.getDescription())
                .isAvailable(entity.isAvailable())
                .ownerId(entity.getOwnerId())
                .isValidated(entity.getIsValidated())
                .numberOfRooms(entity.getNumberOfRooms())
                .capacity(entity.getCapacity())
                .propertyType(entity.getPropertyType())
                .country(entity.getCountry())
                .size(entity.getSize())
                .imageUrls(entity.getImageUrls())
                .contactSlots(entity.getContactSlots())
                .privacyDeclaration(entity.isPrivacyDeclaration())
                .conciergerieType(entity.getConciergerieType())
                .accommodationType(entity.getAccommodationType())
                .pricePerNight(entity.getPricePerNight())
                .city(entity.getCity())
                .numberOfBedrooms(entity.getNumberOfBedrooms())
                .numberOfBathrooms(entity.getNumberOfBathrooms())
                .acceptsPets(entity.isAcceptsPets())
                .acceptsBabies(entity.isAcceptsBabies())
                .build();
    }

    static PropertyEntity fromDomain(Property domain) {
        return PropertyEntity.builder()
                .id(domain.getId())
                .adress(domain.getAdress())
                .propertyName(domain.getPropertyName())
                .description(domain.getDescription())
                .isAvailable(domain.isAvailable())
                .ownerId(domain.getOwnerId())
                .isValidated(domain.getIsValidated())
                .numberOfRooms(domain.getNumberOfRooms())
                .capacity(domain.getCapacity())
                .propertyType(domain.getPropertyType())
                .country(domain.getCountry())
                .size(domain.getSize())
                .imageUrls(domain.getImageUrls())
                .contactSlots(domain.getContactSlots())
                .privacyDeclaration(domain.isPrivacyDeclaration())
                .conciergerieType(domain.getConciergerieType())
                .accommodationType(domain.getAccommodationType())
                .pricePerNight(domain.getPricePerNight())
                .city(domain.getCity())
                .numberOfBathrooms(domain.getNumberOfBathrooms())
                .numberOfBedrooms(domain.getNumberOfBedrooms())
                .acceptsPets(domain.isAcceptsPets())
                .acceptsBabies(domain.isAcceptsBabies())
                .build();
    }
}
