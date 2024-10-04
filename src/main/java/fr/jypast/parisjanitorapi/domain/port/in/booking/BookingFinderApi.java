package fr.jypast.parisjanitorapi.domain.port.in.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BookingFinderApi {
    List<Property> findAvailablePropertiesBetweenDates(Date startDate, Date endDate);
    List<Booking> findBookingsByTenantIdAndEndDateBefore(UUID tenantId, Date date);
    List<Booking> findBookingsByPropertyIdAndEndDateBefore(UUID propertyId, Date date);
    List<Booking> findBookingsByTenantIdAndDatesBetween(UUID tenantId, Date startDate, Date endDate);
    List<Booking> findBookingsByTenantIdAndStartDateAfter(UUID tenantId, Date date);
    List<Booking> findPendingBookingsByTenantId(UUID tenantId);
    List<Booking> findBookingsByTenantIdAndDatesOverlap(UUID tenantId, Date startDate, Date endDate);

}
