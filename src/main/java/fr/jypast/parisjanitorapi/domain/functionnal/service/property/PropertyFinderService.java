package fr.jypast.parisjanitorapi.domain.functionnal.service.property;

import fr.jypast.parisjanitorapi.domain.functionnal.model.property.FavoriteProperty;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.PropertyType;
import fr.jypast.parisjanitorapi.domain.port.in.property.PropertyFinderApi;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PropertyFinderService implements PropertyFinderApi {

    private final PropertyPersistenceSpi spi;

    @Override
    public List<Property> findAll() {
        return spi.findAll();
    }

    @Override
    public Optional<Property> findById(UUID id) {
        return spi.findById(id);
    }

    @Override
    public List<Property> findByDateAndMinSize(List<UUID> ids, double minSize) {
        return spi.findByDateAndMinSize(ids, minSize);
    }

    @Override
    public List<Property> findByDateAndMaxSize(List<UUID> ids, double maxSize) {
        return spi.findByDateAndMaxSize(ids, maxSize);
    }

    @Override
    public List<Property> findBySizeRange(double minSize, double maxSize) {
        return spi.findBySizeRange(minSize, maxSize);
    }

    @Override
    public List<Property> findByCountry(String country) {
        return spi.findByCountry(country);
    }

    @Override
    public List<Property> findByRoomsAndCapacity(List<UUID> ids, int rooms, int capacity) {
        return spi.findByRoomsAndCapacity(ids, rooms, capacity);
    }

    @Override
    public List<Property> findByRooms(int rooms) {
        return spi.findByRooms(rooms);
    }

    @Override
    public List<Property> findByCapacity(int capacity) {
        return spi.findByCapacity(capacity);
    }
    @Override
    public List<Property> findByMinRooms(int minRooms) {
        return spi.findByMinRooms(minRooms);
    }

    @Override
    public List<Property> findByMinCapacity(int minCapacity) {
        return spi.findByMinCapacity(minCapacity);
    }

    @Override
    public List<Property> findByMinRoomsAndCapacity(int minRooms, int minCapacity) {
        return spi.findByMinRoomsAndCapacity(minRooms, minCapacity);
    }
    @Override
    public List<Property> findByCountryAndIds(String country, List<UUID> ids) {
        return spi.findByCountryAndIds(country, ids);
    }

    @Override
    public List<Property> findByCountryAndMinRoomsAndCapacity(String country, List<UUID> ids, int minRooms, int minCapacity) {
        return spi.findByCountryAndMinRoomsAndCapacity(country, ids, minRooms, minCapacity);
    }

    @Override
    public List<Property> findByCountryAndTypeAndRoomsAndCapacity(String country, List<UUID> ids, int rooms, int capacity, PropertyType type) {
        return spi.findByCountryAndTypeAndRoomsAndCapacity(country, ids, rooms, capacity, type);
    }

    @Override
    public List<Property> findAvailableByType(PropertyType type) {
        return spi.findAvailableByType(type);
    }

    @Override
    public List<FavoriteProperty> getUserFavorites(UUID userId) {
        return spi.findByUserId(userId);
    }
}
