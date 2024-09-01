package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PropertyCreatorService implements PropertyCreatorApi {

    private final PropertyPersistenceSpi spi;

    @Override
    public Property createProperty(Property property) {
        log.debug("Creating property: {}", property);
        return spi.save(property);
    }
}
