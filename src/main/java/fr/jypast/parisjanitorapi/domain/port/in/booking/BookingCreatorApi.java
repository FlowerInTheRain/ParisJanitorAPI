package fr.jypast.parisjanitorapi.domain.port.in.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;

import java.util.UUID;

public interface BookingCreatorApi {
    Booking createBooking(UUID userToken, Booking booking);

}
