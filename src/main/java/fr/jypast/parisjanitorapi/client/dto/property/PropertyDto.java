package fr.jypast.parisjanitorapi.client.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PropertyDto(@JsonProperty("id") UUID id,
                          @JsonProperty("address") String address,
                          @JsonProperty("description") String description,
                          @JsonProperty("isAvailable") boolean isAvailable,
                          @JsonProperty("ownerId") UUID ownerId,
                          @JsonProperty("isValidated") boolean isValidated
) {
}
