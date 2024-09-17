package fr.jypast.parisjanitorapi.domain.port.in.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingFinderApi {
    List<Booking> findAvailablePropertiesBetweenDates(LocalDate startDate, LocalDate endDate);
}
