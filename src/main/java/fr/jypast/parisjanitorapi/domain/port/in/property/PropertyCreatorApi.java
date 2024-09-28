package fr.jypast.parisjanitorapi.domain.port.in.property;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.FavoriteProperty;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;

import java.util.UUID;

public interface PropertyCreatorApi {
    Property createProperty(UUID userToken, Property property);
    FavoriteProperty addFavorite(UUID userId, UUID propertyId);

}
