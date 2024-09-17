package fr.jypast.parisjanitorapi.domain.functionnal.service.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BookingFinderService implements BookingFinderApi {

    private final BookingPersistenceSpi bookingPersistenceSpi;
    private final PropertyPersistenceSpi propertyPersistenceSpi;

    @Override
    public List<Property> findAvailablePropertiesBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Property> unavailablePropertyIds = bookingPersistenceSpi.findUnavailablePropertiesBetweenDates(startDate, endDate);
        return propertyPersistenceSpi.findAll().stream()
                .filter(property -> !unavailablePropertyIds.contains(property.getId()))
                .toList();
    }

}
