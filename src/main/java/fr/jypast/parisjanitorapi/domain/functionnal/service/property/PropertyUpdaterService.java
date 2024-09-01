package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyUpdaterApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PropertyUpdaterService implements PropertyUpdaterApi {
    private final PropertyPersistenceSpi propertyPersistenceSpi;

    @Override
    public Property update(Property property) {
        log.debug("Updating property: {}", property);
        return propertyPersistenceSpi.save(property);
    }
}
