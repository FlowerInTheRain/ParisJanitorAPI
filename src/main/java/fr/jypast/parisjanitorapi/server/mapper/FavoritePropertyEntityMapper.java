package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.FavoriteProperty;
import fr.jypast.parisjanitorapi.server.entity.FavoritePropertyEntity;

public interface FavoritePropertyEntityMapper {

    static FavoriteProperty toDomain(FavoritePropertyEntity entity) {
        return FavoriteProperty.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .propertyId(entity.getPropertyId())
                .build();
    }

    static FavoritePropertyEntity fromDomain(FavoriteProperty domain) {
        return FavoritePropertyEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .propertyId(domain.getPropertyId())
                .build();
    }
}