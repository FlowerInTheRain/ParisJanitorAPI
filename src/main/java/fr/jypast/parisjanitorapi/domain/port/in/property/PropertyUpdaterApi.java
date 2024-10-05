package fr.jypast.parisjanitorapi.domain.port.in.property;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.ValidationStatut;

import java.util.UUID;

public interface PropertyUpdaterApi {
    Property update(UUID userToken, Property property);
    Property updateValidationStatus(UUID propertyId, ValidationStatut status);

}

