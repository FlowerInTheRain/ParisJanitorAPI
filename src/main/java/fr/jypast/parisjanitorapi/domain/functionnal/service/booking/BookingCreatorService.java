package fr.jypast.parisjanitorapi.domain.functionnal.service.booking;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class BookingCreatorService implements BookingCreatorApi {

    private final BookingPersistenceSpi spi;
    private final TokenControllerService tokenControllerService;


    @Override
    public Booking createBooking(UUID userToken, Booking booking) {
        User tenant = tokenControllerService.getUserByToken(userToken);

        return spi.save(booking.withTenantId(tenant.getId()))
                .getOrElseThrow(DataNotSaveException::new);
    }

}
