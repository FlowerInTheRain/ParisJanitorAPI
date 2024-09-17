package fr.jypast.parisjanitorapi.domain.port.in.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;

import java.time.LocalDate;
import java.util.List;

public interface BookingFinderApi {
    List<Property> findAvailablePropertiesBetweenDates(LocalDate startDate, LocalDate endDate);
}
