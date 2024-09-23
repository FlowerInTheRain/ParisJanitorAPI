package fr.jypast.parisjanitorapi.domain.functionnal.service.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class BookingFinderService implements BookingFinderApi {

    private final BookingPersistenceSpi bookingPersistenceSpi;
    private final PropertyPersistenceSpi propertyPersistenceSpi;
    
    
    @Override
    public List<Property> findAvailablePropertiesBetweenDates(Date startDate, Date endDate) {
        List<UUID> unavailablePropertyIds = bookingPersistenceSpi.findUnavailablePropertyIdsBetweenDates(startDate, endDate);
        return propertyPersistenceSpi.findAll().stream()
                .filter(property -> !unavailablePropertyIds.contains(property.getId()))
                .collect(Collectors.toList());
    }

}
