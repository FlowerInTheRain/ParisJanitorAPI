package fr.jypast.parisjanitorapi.domain.functionnal.service.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.OccupancyCalendar;
import fr.jypast.parisjanitorapi.domain.port.in.booking.CalendarBlockerApi;
import fr.jypast.parisjanitorapi.domain.port.out.CalendarPersistenceSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalendarBlockerService implements CalendarBlockerApi {

    private final CalendarPersistenceSpi calendarPersistenceSpi;

    @Override
    public List<OccupancyCalendar> getOccupancyCalendar(UUID propertyId) {
        return calendarPersistenceSpi.findByPropertyId(propertyId);
    }

    @Override
    public void blockDates(UUID propertyId, List<Date> dates) {
        List<OccupancyCalendar> existingReservations = calendarPersistenceSpi.findByPropertyIdAndDates(propertyId, dates);
        if (!existingReservations.isEmpty()) {
            throw new IllegalStateException("Some dates are already reserved or unavailable.");
        }

        for (Date date : dates) {
            OccupancyCalendar calendarEntry = OccupancyCalendar.builder()
                    .propertyId(propertyId)
                    .date(date)
                    .isAvailable(false)
                    .build();
            calendarPersistenceSpi.save(calendarEntry);
        }
    }

    @Override
    public void unblockDates(UUID propertyId, List<Date> dates) {
        calendarPersistenceSpi.deleteByPropertyIdAndDates(propertyId, dates);
    }

    @Override
    public List<UUID> findAvailablePropertiesBetweenDates(Date startDate, Date endDate) {
        return calendarPersistenceSpi.findAvailablePropertiesBetweenDates(startDate, endDate);
    }
}
