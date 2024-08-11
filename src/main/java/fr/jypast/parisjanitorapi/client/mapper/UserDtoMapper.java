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
                domain.getPseudo(),
                domain.getToken(),
                domain.getTokenDate(),
                domain.getRoles()
        );
    }

    static User creationRequestToDomain(UserCreationRequest request) {
        return User.builder()
                .id(UUID.randomUUID())
                .email(request.email().trim())
                .password(request.password().trim())
                .pseudo(request.pseudo().trim())
                .build();
    }
}
