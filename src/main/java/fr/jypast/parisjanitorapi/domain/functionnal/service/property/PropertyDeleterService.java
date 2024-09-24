package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.port.out.FilesManagementSpi;
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
    private final FilesManagementSpi filesManagementSpi;
    @Override
    public void deleteById(UUID id) {
        filesManagementSpi.deleteContainer(id.toString());
        spi.deleteById(id);
    }
}
