package fr.jypast.parisjanitorapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OwnerDto(
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("email") String email,
        @JsonProperty("phoneNumber") String phoneNumber
) { }