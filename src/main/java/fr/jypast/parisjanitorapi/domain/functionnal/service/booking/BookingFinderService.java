package fr.jypast.parisjanitorapi.domain.functionnal.service.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.BookingStatus;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
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

    @Override
    public List<Booking> findBookingsByTenantIdAndEndDateBefore(UUID tenantId, Date date) {
        return bookingPersistenceSpi.findByTenantIdAndEndDateBefore(tenantId, date);
    }

    @Override
    public List<Booking> findBookingsByPropertyIdAndEndDateBefore(UUID propertyId, Date date) {
        return bookingPersistenceSpi.findByPropertyIdAndEndDateBefore(propertyId, date);
    }

    @Override
    public List<Booking> findBookingsByTenantIdAndDatesBetween(UUID tenantId, Date startDate, Date endDate) {
        return bookingPersistenceSpi.findByTenantIdAndDatesBetween(tenantId, startDate, endDate);
    }

    @Override
    public List<Booking> findBookingsByTenantIdAndStartDateAfter(UUID tenantId, Date date) {
        return bookingPersistenceSpi.findByTenantIdAndStartDateAfter(tenantId, date);
    }

    @Override
    public List<Booking> findPendingBookingsByTenantId(UUID tenantId) {
        return bookingPersistenceSpi.findPendingBookingsByTenantId(tenantId);
    }

    @Override
    public List<Booking> findBookingsByTenantIdAndDatesOverlap(UUID tenantId, Date startDate, Date endDate) {
        return bookingPersistenceSpi.findByTenantIdAndDatesOverlap(tenantId, startDate, endDate);
    }

    @Override
    public List<Booking> findBookingsByTenantIdAndStatus(UUID tenantId, BookingStatus status) {
        return bookingPersistenceSpi.findByTenantIdAndStatus(tenantId, status);
    }

    @Override
    public List<Booking> findBookingsByTenantIdAndStatuses(UUID tenantId, List<BookingStatus> statuses) {
        return bookingPersistenceSpi.findByTenantIdAndStatuses(tenantId, statuses);
    }

}
