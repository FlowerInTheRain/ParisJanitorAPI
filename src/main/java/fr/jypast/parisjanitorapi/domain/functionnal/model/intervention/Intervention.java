package fr.jypast.parisjanitorapi.domain.functionnal.model.intervention;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class Intervention {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();
    @With
    UUID propertyId;
    @With
    String description;
    @With
    Date date;
    @With
    String serviceProviderId;
}
