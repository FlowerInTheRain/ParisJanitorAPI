package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.server.entity.UserEntity;

public interface UserEntityMapper {

    static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .pseudo(entity.getPseudo())
                .token(entity.getToken())
                .tokenDate(entity.getTokenDate())
                .activated(entity.isActivated())
                .verificationCode(entity.getVerificationCode())
                .build();
    }

    static UserEntity fromDomain(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .pseudo(domain.getPseudo())
                .token(domain.getToken())
                .tokenDate(domain.getTokenDate())
                .activated(domain.isActivated())
                .verificationCode(domain.getVerificationCode())
                .build();
    }

}
