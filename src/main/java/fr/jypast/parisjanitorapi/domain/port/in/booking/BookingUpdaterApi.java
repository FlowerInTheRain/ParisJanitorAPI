package fr.jypast.parisjanitorapi.domain.port.in.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.BookingStatus;

import java.util.UUID;

public interface BookingUpdaterApi {

    Booking updateBookingStatus(UUID bookingId, BookingStatus status, UUID tenantId);

}
