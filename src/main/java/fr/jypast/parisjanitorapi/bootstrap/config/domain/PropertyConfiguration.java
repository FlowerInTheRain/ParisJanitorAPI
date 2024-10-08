package fr.jypast.parisjanitorapi.bootstrap.config.domain;

import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.booking.CalendarBlockerService;
import fr.jypast.parisjanitorapi.domain.port.in.files.FilesManagementApi;
import fr.jypast.parisjanitorapi.domain.functionnal.service.property.PropertyCreatorService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.property.PropertyDeleterService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.property.PropertyFinderService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.property.PropertyUpdaterService;
import fr.jypast.parisjanitorapi.domain.port.in.booking.CalendarBlockerApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyFinderApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyUpdaterApi;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import fr.jypast.parisjanitorapi.domain.port.out.CalendarPersistenceSpi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfiguration {

    @Bean
    public PropertyFinderApi propertyFinderApi(
            PropertyPersistenceSpi spi
    ) {
        return new PropertyFinderService(spi);
    }

    @Bean
    public PropertyCreatorApi propertyCreatorApi(PropertyPersistenceSpi spi,
                                                 TokenControllerService tokenControllerService, FilesManagementApi filesManagementApi) {
        return new PropertyCreatorService(spi, tokenControllerService, filesManagementApi);
    }

    @Bean
    public PropertyDeleterApi propertyDeleterApi(PropertyPersistenceSpi spi, FilesManagementApi filesManagementApiService) {
        return new PropertyDeleterService(spi, filesManagementApiService);
    }

    @Bean
    public PropertyUpdaterApi propertyUpdaterApi(PropertyPersistenceSpi spi) {
        return new PropertyUpdaterService(spi);
    }

    @Bean
    public CalendarBlockerApi calendarBlockerApi(CalendarPersistenceSpi calendarSpi,
                                                 BookingPersistenceSpi bookingSpi,
                                                 PropertyPersistenceSpi propertySpi) {
        return new CalendarBlockerService(calendarSpi, bookingSpi, propertySpi);
    }}
