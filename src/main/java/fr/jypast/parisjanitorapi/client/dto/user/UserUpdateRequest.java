package fr.jypast.parisjanitorapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserUpdateRequest(
        @NotNull @JsonProperty("email") String email,
        @NotNull @JsonProperty("lastName") String lastName,
        @NotNull @JsonProperty("firstName") String firstName,
        @JsonProperty("birthday") String birthday,
        @JsonProperty("phoneNumber") String phoneNumber,
        @JsonProperty("region") String region,
        @JsonProperty("adresse1") String adresse1,
        @JsonProperty("adresse2") String adresse2,
        @JsonProperty("profilePicture") String profilePicture

) {}
