package fr.jypast.parisjanitorapi.domain.port.in.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.OccupancyCalendar;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CalendarBlockerApi {
    List<OccupancyCalendar> getOccupancyCalendar(UUID propertyId);
    void blockDates(UUID propertyId, List<LocalDate> dates);
    void unblockDates(UUID propertyId, List<LocalDate> dates);

    List<UUID> findAvailablePropertiesBetweenDates(LocalDate startDate, LocalDate endDate);

}
