package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingPersistenceSpi extends PersistenceSpi<Booking, UUID> {
    Either<ApplicationError, Booking> save(Booking booking);
    List<Booking> findByPropertyIdAndDates(UUID propertyId, LocalDate startDate, LocalDate endDate);
    List<Booking> findBookingsBetweenDates(LocalDate startDate, LocalDate endDate);
    List<Booking> findPropertiesNotIn(List<UUID> propertyIds);
    List<Property> findUnavailablePropertiesBetweenDates(LocalDate startDate, LocalDate endDate);
}
