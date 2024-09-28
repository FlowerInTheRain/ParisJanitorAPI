package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.functionnal.exception.DataNotSaveException;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.FavoriteProperty;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.functionnal.model.user.User;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.out.FilesManagementSpi;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyCreatorApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component

public class PropertyCreatorService implements PropertyCreatorApi {

    private final PropertyPersistenceSpi spi;
    private final TokenControllerService tokenControllerService;
    private final FilesManagementSpi filesManagementSpi;

    @Override
    public Property createProperty(UUID userToken, Property property) {
        User owner = tokenControllerService.getUserByToken(userToken);
        filesManagementSpi.createContainer(owner.getId().toString());
        return spi.save(property.withOwnerId(owner.getId()))
                .getOrElseThrow(DataNotSaveException::new);

    }

    private void validateProperty(Property property) {
        if (property.getAdress() == null || property.getAdress().isBlank()) {
            throw new IllegalArgumentException("Property address cannot be empty.");
        }
    }

    @Override
    public FavoriteProperty addFavorite(UUID userId, UUID propertyId) {
        FavoriteProperty favorite = FavoriteProperty.builder()
                .userId(userId)
                .propertyId(propertyId)
                .build();
        return spi.save(favorite);
    }
}
