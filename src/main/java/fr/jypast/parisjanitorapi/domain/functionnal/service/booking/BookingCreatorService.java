package fr.jypast.parisjanitorapi.domain.functionnal.service.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BookingCreatorService implements BookingCreatorApi {

    private final BookingPersistenceSpi bookingPersistenceSpi;

    @Override
    public Booking createBooking(Booking booking) {
        List<Booking> existingBookings = bookingPersistenceSpi.findByPropertyIdAndDates(
                booking.getPropertyId(),
                booking.getStartDate(),
                booking.getEndDate()
        );
        if (!existingBookings.isEmpty()) {
            throw new IllegalStateException("The property is already booked for the given dates.");
        }

        return bookingPersistenceSpi.save(booking)
                .getOrElseThrow(DataNotSaveException::new);
    }
}
