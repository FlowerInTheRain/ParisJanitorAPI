package fr.jypast.parisjanitorapi.domain.functionnal.service.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
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
        User tenant = tokenControllerService.getUserByToken(userToken);

        List<Booking> overlappingBookings = spi.findByTenantIdAndDatesOverlap(
                tenant.getId(),
                booking.getStartDate(),
                booking.getEndDate()
        );

        if (!overlappingBookings.isEmpty()) {
            throw new IllegalStateException("You cannot book multiple properties during overlapping dates.");
        }

        // Vérifier si le logement est disponible pour les dates spécifiées
        List<Booking> existingBookings = spi.findByPropertyIdAndDates(booking.getPropertyId(), booking.getStartDate(), booking.getEndDate());
        if (!existingBookings.isEmpty()) {
            throw new IllegalStateException("The property is already booked for the given dates.");
        }

        // Enregistrer la réservation avec tenantId défini
        return spi.save(booking.withTenantId(tenant.getId())).getOrElseThrow(DataNotSaveException::new);
    }



}
