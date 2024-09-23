package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.PropertyType;
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
<<<<<<< HEAD
    List<Property> findByIds(List<UUID> ids);
=======
    List<UUID> findAllPropertyIds();
    List<UUID> findAvailablePropertiesByExcludingIds(List<UUID> bookedPropertyIds);
    List<Property> findByDateAndMinSize(List<UUID> ids, double minSize);
    List<Property> findByDateAndMaxSize(List<UUID> ids, double maxSize);
    List<Property> findByCountry(String country);
    List<Property> findBySizeRange(double minSize, double maxSize);
    List<Property> findByRoomsAndCapacity(List<UUID> ids, int rooms, int capacity);
    List<Property> findByRooms(int rooms);
    List<Property> findByCapacity(int capacity);
    List<Property> findByMinRooms(int minRooms);
    List<Property> findByMinCapacity(int minCapacity);
    List<Property> findByMinRoomsAndCapacity(int minRooms, int minCapacity);
    List<Property> findByCountryAndIds(String country, List<UUID> ids);
    List<Property> findByCountryAndMinRoomsAndCapacity(String country, List<UUID> ids, int minRooms, int minCapacity);
    List<Property> findByCountryAndTypeAndRoomsAndCapacity(String country, List<UUID> ids, int rooms, int capacity, PropertyType type);

>>>>>>> upstream/main
}
