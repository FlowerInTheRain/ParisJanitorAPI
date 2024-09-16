package fr.jypast.parisjanitorapi.domain.functionnal.model.booking;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class OccupancyCalendar {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();

    @With
    UUID propertyId;

    @With
    LocalDate date;

    @With
    boolean isAvailable;
}
