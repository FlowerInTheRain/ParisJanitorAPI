package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.port.in.files.FilesManagementApi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyDeleterApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class PropertyDeleterService implements PropertyDeleterApi {
    private final PropertyPersistenceSpi spi;
    private final FilesManagementApi filesManagementApi;
    @Override
    public void deleteById(UUID id) {
        filesManagementApi.deleteContainer(id.toString());
        spi.deleteById(id);
    }

    @Override
    public void removeFavorite(UUID userId, UUID propertyId) {
        spi.deleteByUserIdAndPropertyId(userId, propertyId);
    }
}
