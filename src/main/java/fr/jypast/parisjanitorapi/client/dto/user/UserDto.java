package fr.jypast.parisjanitorapi.client.dto.user;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.UserRole;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.UserStatut;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UserDto(
        UUID id,
        String email,
        String lastName,
        String firstName,
        String birthday,
        String phoneNumber,
        String region,
        String adresse1,
        String adresse2,
        UUID token,
        LocalDate tokenDate,
        List<UserRole> roles,
        List<UserStatut> statut,
        boolean activated,
        String verificationCode,
        String passwordVerification,
        String profilePicture
) { }
