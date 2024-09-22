package fr.jypast.parisjanitorapi.client.dto.property;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record PropertyCreationRequest(
        @NotNull @JsonProperty("address") String address,
        @NotNull @JsonProperty("description") String description,
        @NotNull @JsonProperty("numberOfRooms") int numberOfRooms,
        @NotNull @JsonProperty("capacity") int capacity,
        @NotNull @JsonProperty("propertyType") String propertyType,
        @NotNull @JsonProperty("country") String country,
        @NotNull @JsonProperty("size") double size,
        @NotNull @JsonProperty("imageUrls") List<String> imageUrls,
        @NotNull @JsonProperty("contactSlots") List<String> contactSlots,
        @NotNull @JsonProperty("privacyDeclaration") boolean privacyDeclaration,
        @NotNull @JsonProperty("conciergerieType") String conciergerieType
) { }
