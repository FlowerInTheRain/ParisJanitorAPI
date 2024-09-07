package fr.jypast.parisjanitorapi.client.dto.property;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record PropertyCreationRequest(
    @NotNull @JsonProperty("adress") String adress,
    @NotNull @JsonProperty("description") String description,
    @NotNull @JsonProperty("ownerId") UUID ownerId
) { }
