package fr.jypast.parisjanitorapi.domain.functionnal.model.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class OccupancyCalendar {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    Property property;

    @Column(nullable = false)
    @With
    Date date;

    @Column(nullable = false)
    @With
    UUID propertyId;

    @With
    boolean isAvailable;
}
