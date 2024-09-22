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
    List<UUID> findAllPropertyIds();
    List<UUID> findAvailablePropertiesByExcludingIds(List<UUID> bookedPropertyIds);
    List<Property> findByDateAndMinSize(List<UUID> ids, double minSize);
    List<Property> findByDateAndMaxSize(List<UUID> ids, double maxSize);
    List<Property> findByCountry(String country);
    List<Property> findBySizeRange(double minSize, double maxSize);
    List<Property> findByRoomsAndCapacity(List<UUID> ids, int rooms, int capacity);

}
