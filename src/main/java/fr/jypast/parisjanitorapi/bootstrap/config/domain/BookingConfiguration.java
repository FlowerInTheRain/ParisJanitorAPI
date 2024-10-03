package fr.jypast.parisjanitorapi.bootstrap.config.domain;

import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.booking.BookingCreatorService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.booking.BookingFinderService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.booking.BookingUpdaterService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingFinderApi;
import fr.jypast.parisjanitorapi.domain.port.in.booking.BookingUpdaterApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingConfiguration {

    @Bean
    public BookingCreatorApi bookingCreatorApi(BookingPersistenceSpi spi,
                                               TokenControllerService tokenControllerService) {
        return new BookingCreatorService(spi, tokenControllerService);
    }

    @Bean
    public BookingFinderApi bookingFinderApi(BookingPersistenceSpi bookingPersistenceSpi,
                                             PropertyPersistenceSpi propertyPersistenceSpi) {
        return new BookingFinderService(bookingPersistenceSpi, propertyPersistenceSpi);
    }

    @Bean
    public BookingUpdaterApi bookingUpdaterApi(
            BookingPersistenceSpi spi,
            TokenControllerService tokenControllerService
    ) {
        return new BookingUpdaterService(spi, tokenControllerService);
    }
}
