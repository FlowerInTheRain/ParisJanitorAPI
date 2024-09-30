package fr.jypast.parisjanitorapi.client.mapper;

import fr.jypast.parisjanitorapi.client.dto.user.UserCreationRequest;
import fr.jypast.parisjanitorapi.client.dto.user.UserDto;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;

import java.util.UUID;

public interface UserDtoMapper {

    static UserDto toDto(User domain) {
        return new UserDto(
                domain.getId(),
                domain.getEmail(),
                domain.getLastName(),
                domain.getFirstName(),
                domain.getBirthday(),
                domain.getPhoneNumber(),
                domain.getRegion(),
                domain.getAdresse1(),
                domain.getAdresse2(),
                domain.getToken(),
                domain.getTokenDate(),
                domain.getRoles(),
                domain.getStatut(),
                domain.isActivated(),
                domain.getVerificationCode(),
                domain.getPasswordVerification()
        );
    }

    static User creationRequestToDomain(UserCreationRequest request) {
        return User.builder()
                .id(UUID.randomUUID())
                .email(request.email().trim())
                .lastName(request.lastName().trim())
                .firstName(request.firstName().trim())
                .birthday(request.birthday())
                .password(request.password() != null ? request.password().trim() : null)
                .phoneNumber(request.phoneNumber() != null ? request.phoneNumber().trim() : null)
                .region(request.region() != null ? request.region().trim() : null)
                .adresse1(request.adresse1() != null ? request.adresse1().trim() : null)
                .adresse2(request.adresse2() != null ? request.adresse2().trim() : null)
                .profilePicture(request.profilePicture() != null ? request.profilePicture().trim() : null)
                .build();
    }

}
