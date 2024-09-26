package fr.jypast.parisjanitorapi.client.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record PropertyDto(
        @JsonProperty("id") UUID id,
        @JsonProperty("adress") String adress,
        @JsonProperty("description") String description,
        @JsonProperty("isAvailable") boolean isAvailable,
        @JsonProperty("ownerId") UUID ownerId,
        @JsonProperty("isValidated") boolean isValidated,
        @JsonProperty("numberOfRooms") int numberOfRooms,
        @JsonProperty("capacity") int capacity,
        @JsonProperty("propertyType") String propertyType,
        @JsonProperty("country") String country,
        @JsonProperty("size") double size,
        @JsonProperty("imageUrls") List<String> imageUrls,
        @JsonProperty("contactSlots") List<String> contactSlots,
        @JsonProperty("privacyDeclaration") boolean privacyDeclaration,
        @JsonProperty("conciergerieType") String conciergerieType,
        @JsonProperty("pricePerNight") double pricePerNight,
        @JsonProperty("city") String city,
        @JsonProperty("accommodationType") String accommodationType
) {
}
