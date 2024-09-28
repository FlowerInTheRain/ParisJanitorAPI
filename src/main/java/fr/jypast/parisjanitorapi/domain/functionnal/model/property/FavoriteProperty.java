package fr.jypast.parisjanitorapi.domain.functionnal.model.property;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class FavoriteProperty {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();

    @With
    UUID userId;

    @With
    UUID propertyId;
}