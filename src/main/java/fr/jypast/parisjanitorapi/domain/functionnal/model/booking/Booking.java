package fr.jypast.parisjanitorapi.domain.functionnal.model.booking;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.sql.Date;
import java.util.UUID;

@Value
@Builder
public class Booking {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();
    @With
    UUID propertyId;
    @With
    Date startDate;
    @With
    Date endDate;
    @With
    UUID tenantId;
    @With
    BookingStatus status;
}
