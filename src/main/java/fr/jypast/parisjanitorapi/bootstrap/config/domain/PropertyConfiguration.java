package fr.jypast.parisjanitorapi.bootstrap.config.domain;

import fr.jypast.parisjanitorapi.domain.functionnal.service.property.PropertyCreatorService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.property.PropertyDeleterService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.property.PropertyUpdaterService;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyUpdaterApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfiguration {

    @Bean
    public PropertyCreatorApi propertyCreatorApi(PropertyPersistenceSpi spi) {
        return new PropertyCreatorService(spi);
    }

    @Bean
    public PropertyDeleterApi propertyDeleterApi(PropertyPersistenceSpi spi) {
        return new PropertyDeleterService(spi);
    }

    @Bean
    public PropertyUpdaterApi propertyUpdaterApi(PropertyPersistenceSpi spi) {
        return new PropertyUpdaterService(spi);
    }
}
