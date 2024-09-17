package fr.jypast.parisjanitorapi.domain.port.in.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.OccupancyCalendar;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CalendarBlockerApi {
    List<OccupancyCalendar> getOccupancyCalendar(UUID propertyId);
    void blockDates(UUID propertyId, List<Date> dates);
    void unblockDates(UUID propertyId, List<Date> dates);

    List<UUID> findAvailablePropertiesBetweenDates(Date startDate, Date endDate);

}
