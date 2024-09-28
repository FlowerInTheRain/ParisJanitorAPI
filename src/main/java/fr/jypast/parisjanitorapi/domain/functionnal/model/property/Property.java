package fr.jypast.parisjanitorapi.domain.functionnal.model.property;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Property {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();

    @With
    String adress;

    @With
    String description;

    @With
    boolean isAvailable;

    @With
    UUID ownerId;

    @With
    List<String> imageUrls;

    @With
    String country;

    @With
    double size;

    @With
    PropertyType propertyType;

    @With
    AccommodationType accommodationType;

    @With
    int numberOfRooms;

    @With
    int capacity;

    @With
    List<ContactSlot> contactSlots;

    @With
    boolean privacyDeclaration;

    @With
    ConciergerieType conciergerieType;

    @With
    double pricePerNight;

    @With
    String city;

    @Builder.Default
    @With
    boolean isValidated = false;
}
