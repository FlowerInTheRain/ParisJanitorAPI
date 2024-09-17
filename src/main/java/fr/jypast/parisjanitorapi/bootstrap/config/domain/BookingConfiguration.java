package fr.jypast.parisjanitorapi.bootstrap.config.domain;

import fr.jypast.parisjanitorapi.domain.functionnal.service.booking.BookingCreatorService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.booking.BookingFinderService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingConfiguration {

    @Bean
    public BookingCreatorApi bookingCreatorApi(BookingPersistenceSpi bookingPersistenceSpi) {
        return new BookingCreatorService(bookingPersistenceSpi);
    }

    @Bean
    public BookingFinderApi bookingFinderApi(BookingPersistenceSpi bookingPersistenceSpi) {
        return new BookingFinderService(bookingPersistenceSpi);
    }
}
