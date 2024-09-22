package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyFinderApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PropertyFinderService implements PropertyFinderApi {

    private final PropertyPersistenceSpi spi;

    @Override
    public List<Property> findAll() {
        return spi.findAll();
    }

    @Override
    public Optional<Property> findById(UUID id) {
        return spi.findById(id);
    }

    @Override
    public List<Property> findByDateAndMinSize(List<UUID> ids, double minSize) {
        return spi.findByDateAndMinSize(ids, minSize);
    }

    @Override
    public List<Property> findByDateAndMaxSize(List<UUID> ids, double maxSize) {
        return spi.findByDateAndMaxSize(ids, maxSize);
    }

}
