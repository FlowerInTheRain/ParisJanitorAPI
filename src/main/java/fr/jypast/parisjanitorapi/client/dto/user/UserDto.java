package fr.jypast.parisjanitorapi.client.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.UserRole;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.UserStatut;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public record UserDto(
        @JsonProperty("id") UUID id,
        @JsonProperty("email") String email,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("birthday") String birthday,
        @JsonProperty("phoneNumber") String phoneNumber,
        @JsonProperty("region") String region,
        @JsonProperty("adresse1") String adresse1,
        @JsonProperty("adresse2") String adresse2,
        @JsonProperty("token") UUID token,
        @JsonProperty("tokenDate") Date tokenDate,
        @JsonProperty("roles") List<UserRole> roles,
        @JsonProperty("statut") List<UserStatut> statut,
        @JsonProperty("activated") boolean activated,
        @JsonProperty("verificationCode") String verificationCode,
        @JsonProperty("passwordVerification") String passwordVerification
) { }