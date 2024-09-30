package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import io.vavr.control.Either;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface BookingPersistenceSpi extends PersistenceSpi<Booking, UUID> {
    Either<ApplicationError, Booking> save(Booking booking);
    List<Booking> findByPropertyIdAndDates(UUID propertyId, Date startDate, Date endDate);
    List<UUID> findBookedPropertyIdsBetweenDates(Date startDate, Date endDate);
    List<Booking> findPropertiesNotIn(List<UUID> propertyIds);
    List<UUID> findUnavailablePropertyIdsBetweenDates(Date startDate, Date endDate);
}
