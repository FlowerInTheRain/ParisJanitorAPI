package fr.jypast.parisjanitorapi.domain.functionnal.model.booking;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Value
@Builder
@Getter
@Setter
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
    int numberOfAdults;
    @With
    int numberOfChildren;
    @With
    int numberOfBabies;
    @With
    int numberOfPets;
    @With
    BookingStatus status;
    @With
    List<String> propertyImages;
}
