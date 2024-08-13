package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.server.entity.UserEntity;

public interface UserEntityMapper {

    static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .birthday(entity.getBirthday())
                .phoneNumber(entity.getPhoneNumber())
                .region(entity.getRegion())
                .adresse1(entity.getAdresse1())
                .adresse2(entity.getAdresse2())
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
                .lastName(domain.getLastName())
                .firstName(domain.getFirstName())
                .birthday(domain.getBirthday())
                .phoneNumber(domain.getPhoneNumber())
                .region(domain.getRegion())
                .adresse1(domain.getAdresse1())
                .adresse2(domain.getAdresse2())
                .token(domain.getToken())
                .tokenDate(domain.getTokenDate())
                .activated(domain.isActivated())
                .verificationCode(domain.getVerificationCode())
                .build();
    }
}
