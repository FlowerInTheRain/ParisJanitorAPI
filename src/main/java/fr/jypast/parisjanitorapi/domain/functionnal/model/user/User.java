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
    String password;

    @With
    String pseudo;

    @With
    UUID token;

    @With
    LocalDate tokenDate;

    @With
    List<UserRole> roles;

    @Builder.Default
    @With
    boolean activated = false;

    @Builder.Default
    @With
    String verificationCode = RandomStringGenerator.generateAlphanumericString(10);

}