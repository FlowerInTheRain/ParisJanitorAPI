package fr.jypast.parisjanitorapi.domain.port.in.property;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.FavoriteProperty;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.PropertyType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PropertyFinderApi {

    List<Property> findAll();
    Optional<Property> findById(UUID id);
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
    List<Property> findAvailableByType(PropertyType type);
    List<FavoriteProperty> getUserFavorites(UUID userId);

}
