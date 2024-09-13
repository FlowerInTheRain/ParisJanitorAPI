package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import io.vavr.control.Either;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyPersistenceSpi extends PersistenceSpi<Property, UUID> {
    Either<ApplicationError, Property> save(Property property);
    List<Property> findAll();
    Optional<Property> findById(UUID id);
    void deleteById(UUID id);
    Property update(Property o);
}
