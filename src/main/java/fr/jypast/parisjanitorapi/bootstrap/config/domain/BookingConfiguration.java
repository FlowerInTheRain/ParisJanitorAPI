package fr.jypast.parisjanitorapi.bootstrap.config.domain;

import fr.jypast.parisjanitorapi.domain.functionnal.service.booking.BookingCreatorService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.booking.BookingFinderService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import org.springframework.context.annotation.Bean;

public class BookingConfiguration {

    @Bean
    public BookingCreatorApi bookingCreatorApi(BookingPersistenceSpi spi) {
        return new BookingCreatorService(spi);
    }
    @Bean
    public BookingFinderApi bookingFinderApi(BookingPersistenceSpi spi) {
        return new BookingFinderService(spi);
    }
}
