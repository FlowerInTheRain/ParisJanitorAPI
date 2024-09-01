package fr.jypast.parisjanitorapi.domain.port.in.property;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;

public interface PropertyUpdaterApi {
    Property update(Property property);
}

