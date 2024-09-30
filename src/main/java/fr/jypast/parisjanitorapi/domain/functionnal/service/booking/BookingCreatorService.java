package fr.jypast.parisjanitorapi.domain.functionnal.service.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class BookingCreatorService implements BookingCreatorApi {

    private final BookingPersistenceSpi spi;
    private final TokenControllerService tokenControllerService;

    @Override
    public Booking createBooking(UUID userToken, Booking booking) {
        List<Booking> overlappingBookings = spi.findByTenantIdAndDatesOverlap(
                booking.getTenantId(), booking.getStartDate(), booking.getEndDate());

        if (!overlappingBookings.isEmpty()) {
            throw new IllegalStateException("You already have a booking during these dates.");
        }

        return spi.save(booking)
                .getOrElseThrow(DataNotSaveException::new);
    }
}
