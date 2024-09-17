package fr.jypast.parisjanitorapi.domain.port.in.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;

public interface BookingCreatorApi {
    Booking createBooking(Booking booking);

}
