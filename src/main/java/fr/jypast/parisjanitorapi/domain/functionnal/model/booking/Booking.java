package fr.jypast.parisjanitorapi.domain.functionnal.model.booking;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class Booking {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();
    @With
    private UUID propertyId;
    @With
    private LocalDate startDate;
    @With
    private LocalDate endDate;
    @With
    private UUID tenantId;
    @With
    private BookingStatus status;
}
