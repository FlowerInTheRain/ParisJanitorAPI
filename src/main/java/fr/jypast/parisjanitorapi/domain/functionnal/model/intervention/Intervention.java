package fr.jypast.parisjanitorapi.domain.functionnal.model.intervention;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class Intervention {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();
    @With
    private UUID propertyId;
    @With
    private String description;
    @With
    private LocalDateTime date;
    @With
    private String serviceProviderId;
}
