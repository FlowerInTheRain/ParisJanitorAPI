package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;

import java.util.UUID;

public interface PropertyPersistenceSpi extends PersistenceSpi<Property, UUID> {
}
