package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyUpdaterApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class PropertyUpdaterService implements PropertyUpdaterApi {
    private final PropertyPersistenceSpi spi;

    @Override
    public Property update(UUID userToken, Property newProperty) {
        log.debug("Updating property: {}", newProperty);

        Property currentProperty = spi.findById(newProperty.getId())
                .orElseThrow(() -> new DataNotSaveException());

        Property patchedProperty = currentProperty
                .withAdress(newProperty.getAdress() != null ? newProperty.getAdress() : currentProperty.getAdress())
                .withDescription(newProperty.getDescription() != null ? newProperty.getDescription() : currentProperty.getDescription())
                .withOwnerId(newProperty.getOwnerId() != null ? newProperty.getOwnerId() : currentProperty.getOwnerId());

        return spi.save(patchedProperty)
                .getOrElseThrow(DataNotSaveException::new);
    }
}
