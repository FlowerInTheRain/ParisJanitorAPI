package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import io.vavr.control.Either;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BookingPersistenceSpi extends PersistenceSpi<Booking, UUID> {
    Either<ApplicationError, Booking> save(Booking booking);
    List<Booking> findByPropertyIdAndDates(UUID propertyId, Date startDate, Date endDate);
    List<UUID> findBookedPropertyIdsBetweenDates(Date startDate, Date endDate);
    List<Booking> findPropertiesNotIn(List<UUID> propertyIds);
    List<UUID> findUnavailablePropertyIdsBetweenDates(Date startDate, Date endDate);
    List<Booking> findByTenantIdAndDatesOverlap(UUID tenantId, Date startDate, Date endDate);
    List<Booking> findByTenantIdAndEndDateBefore(UUID tenantId, Date date);
    List<Booking> findByPropertyIdAndEndDateBefore(UUID propertyId, Date date);
    List<Booking> findByTenantIdAndDatesBetween(UUID tenantId, Date startDate, Date endDate);
    List<Booking> findByTenantIdAndStartDateAfter(UUID tenantId, Date date);
    List<Booking> findPendingBookingsByTenantId(UUID tenantId);

}
