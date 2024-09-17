package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.OccupancyCalendar;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CalendarPersistenceSpi extends PersistenceSpi<OccupancyCalendar, UUID> {
    List<OccupancyCalendar> findByPropertyId(UUID propertyId);
    List<OccupancyCalendar> findByPropertyIdAndDates(UUID propertyId, List<Date> dates);
    List<UUID> findAvailablePropertiesBetweenDates(Date startDate, Date endDate);
    void deleteByPropertyIdAndDates(UUID propertyId, List<Date> dates);

}
