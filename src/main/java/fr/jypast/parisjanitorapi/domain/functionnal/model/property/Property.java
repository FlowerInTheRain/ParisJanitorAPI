package fr.jypast.parisjanitorapi.domain.functionnal.model.property;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Property {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();

    @With
    String address;

    @With
    String description;

    @With
    boolean isAvailable;

    @With
    UUID ownerId;

    @With
    List<String> imageUrls;

    @Builder.Default
    @With
    boolean isValidated = false;
}
