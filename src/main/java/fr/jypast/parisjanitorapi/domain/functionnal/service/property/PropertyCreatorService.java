package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PropertyCreatorService implements PropertyCreatorApi {

    private final PropertyPersistenceSpi spi;
    private final TokenControllerService tokenControllerService;

    @Override
    public Property createProperty(UUID userToken, Property property) {
        User owner = tokenControllerService.getUserByToken(userToken);

        return spi.save(property.withOwnerId(owner.getId()))
                .getOrElseThrow(DataNotSaveException::new);

    }

    private void validateProperty(Property property) {
        if (property.getAdress() == null || property.getAdress().isBlank()) {
            throw new IllegalArgumentException("Property address cannot be empty.");
        }
    }
}
