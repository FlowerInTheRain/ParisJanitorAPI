package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PropertyDeleterService implements PropertyDeleterApi {
    private final PropertyPersistenceSpi spi;

    @Override
    public void deleteById(UUID id) {
        spi.deleteById(id);
    }

    @Override
    public void removeFavorite(UUID userId, UUID propertyId) {
        spi.deleteByUserIdAndPropertyId(userId, propertyId);
    }
}
