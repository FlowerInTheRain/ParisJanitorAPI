package fr.jypast.parisjanitorapi.domain.functionnal.service.calendar;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.OccupancyCalendar;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.port.in.booking.CalendarBlockerApi;
import fr.jypast.parisjanitorapi.domain.port.in.calendar.OccupancyCalendarApi;
import fr.jypast.parisjanitorapi.domain.port.out.CalendarPersistenceSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void blockDates(UUID propertyId, List<LocalDate> dates) {
        List<OccupancyCalendar> existingReservations = calendarPersistenceSpi.findByPropertyIdAndDates(propertyId, dates);
        if (!existingReservations.isEmpty()) {
            throw new IllegalStateException("Some dates are already reserved or unavailable.");
        }

        for (LocalDate date : dates) {
            OccupancyCalendar calendarEntry = OccupancyCalendar.builder()
                    .property(Property.builder().id(propertyId).build())
                    .date(date)
                    .isAvailable(false)
                    .build();
            calendarPersistenceSpi.save(calendarEntry);
        }
    }

    @Override
    public void unblockDates(UUID propertyId, List<LocalDate> dates) {
        calendarPersistenceSpi.deleteByPropertyIdAndDates(propertyId, dates);
    }
}
