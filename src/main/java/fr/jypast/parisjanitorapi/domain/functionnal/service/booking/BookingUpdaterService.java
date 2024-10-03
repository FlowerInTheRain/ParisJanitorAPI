package fr.jypast.parisjanitorapi.domain.functionnal.service.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.BookingStatus;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingUpdaterApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class BookingUpdaterService implements BookingUpdaterApi {

    private final BookingPersistenceSpi spi;
    private final TokenControllerService tokenControllerService;

    @Override
    public Booking updateBookingStatus(UUID bookingId, BookingStatus status, UUID tenantId) {
        Optional<Booking> optionalBooking = spi.findById(bookingId);

        if (optionalBooking.isEmpty()) {
            throw new IllegalStateException("Booking not found.");
        }

        Booking booking = optionalBooking.get();

        if (!booking.getTenantId().equals(tenantId)) {
            throw new IllegalStateException("You are not authorized to modify this booking.");
        }

        Booking updatedBooking = booking.withStatus(status);

        return spi.save(updatedBooking)
                .getOrElseThrow(() -> new DataNotSaveException());
    }

}
