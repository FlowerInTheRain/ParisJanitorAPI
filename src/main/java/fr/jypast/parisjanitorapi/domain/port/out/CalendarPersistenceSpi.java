package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.OccupancyCalendar;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CalendarPersistenceSpi extends PersistenceSpi<OccupancyCalendar, UUID> {
    List<OccupancyCalendar> findByPropertyId(UUID propertyId);
    List<OccupancyCalendar> findByPropertyIdAndDates(UUID propertyId, List<LocalDate> dates);
    List<UUID> findAvailablePropertiesBetweenDates(LocalDate startDate, LocalDate endDate);
    void deleteByPropertyIdAndDates(UUID propertyId, List<LocalDate> dates);

}
