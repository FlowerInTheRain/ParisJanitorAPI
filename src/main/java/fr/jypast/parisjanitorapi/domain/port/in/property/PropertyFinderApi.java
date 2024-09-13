package fr.jypast.parisjanitorapi.domain.port.in.property;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyFinderApi {

    List<Property> findAll();

    Optional<Property> findById(UUID id);
}
