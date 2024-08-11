package fr.jypast.parisjanitorapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.UserRole;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UserDto(
        @JsonProperty("id") UUID id,
        @JsonProperty("email") String email,
        @JsonProperty("pseudo") String pseudo,
        @JsonProperty("token") UUID token,
        @JsonProperty("tokenDate") LocalDate tokenDate,
        @JsonProperty("roles") List<UserRole> roles
) { }