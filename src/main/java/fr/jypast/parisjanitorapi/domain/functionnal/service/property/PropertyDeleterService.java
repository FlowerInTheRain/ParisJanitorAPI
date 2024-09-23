package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.functionnal.service.files.FilesManagement;
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
    private final FilesManagement filesManagement;
    @Override
    public void deleteById(UUID id) {
        filesManagement.deleteContainer(id.toString());
        spi.deleteById(id);
    }
}
