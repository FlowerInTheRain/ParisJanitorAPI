package fr.jypast.parisjanitorapi.domain.functionnal.model.user;

import fr.jypast.parisjanitorapi.domain.functionnal.service.RandomStringGenerator;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class User {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();

    @With
    String email;

    @With
    String lastName;

    @With
    String firstName;

    @With
    String birthday;

    @With
    String password;

    @With
    String phoneNumber;

    @With
    String region;

    @With
    String adresse1;

    @With
    String adresse2;

    @With
    UUID token;

    @With
    LocalDate tokenDate;

    @With
    List<UserRole> roles;

    @With
    List<UserStatut> statut;

    @Builder.Default
    @With
    boolean activated = true;

    @Builder.Default
    @With
    String verificationCode = RandomStringGenerator.generateAlphanumericString(6);

    @Builder.Default
    @With
    String passwordVerification = RandomStringGenerator.generateAlphanumericString(7);

}