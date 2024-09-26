package fr.jypast.parisjanitorapi.server.adapter;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.PropertyType;
import fr.jypast.parisjanitorapi.domain.port.out.PropertyPersistenceSpi;
import fr.jypast.parisjanitorapi.server.mapper.PropertyEntityMapper;
import fr.jypast.parisjanitorapi.server.repository.PropertyRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class PropertyDatabaseAdapter implements PropertyPersistenceSpi {

    private final PropertyRepository repository;

    @Override
    @Transactional
    public Either<ApplicationError, Property> save(Property o) {
        return Try(() -> repository.save(PropertyEntityMapper.fromDomain(o)))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Erreur lors de l'écriture en BDD", null, o, throwable))
                .map(PropertyEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public List<Property> findAll() {
        return repository.findAll().stream().map(PropertyEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Optional<Property> findById(UUID id) {
        return repository.findById(id).map(PropertyEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Property update(Property o) {
        return PropertyEntityMapper.toDomain(repository.save(PropertyEntityMapper.fromDomain(o)));
    }

    @Override
    @Transactional
    public List<UUID> findAllPropertyIds() {
        return repository.findAllPropertyIds();
    }

    @Override
    @Transactional
    public List<UUID> findAvailablePropertiesByExcludingIds(List<UUID> bookedPropertyIds) {
        return repository.findAvailablePropertiesByExcludingIds(bookedPropertyIds);
    }

    @Override
    @Transactional
    public List<Property> findByDateAndMinSize(List<UUID> ids, double minSize) {
        return repository.findByIdInAndSizeGreaterThanEqual(ids, minSize).stream()
                .map(PropertyEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Property> findByDateAndMaxSize(List<UUID> ids, double maxSize) {
        return repository.findByIdInAndSizeLessThanEqual(ids, maxSize).stream()
                .map(PropertyEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Property> findBySizeRange(double minSize, double maxSize) {
        return repository.findBySizeBetween(minSize, maxSize)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findByCountry(String country) {
        return repository.findByCountry(country)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findByRoomsAndCapacity(List<UUID> ids, int rooms, int capacity) {
        if (ids != null && !ids.isEmpty()) {
            return repository.findByIdInAndNumberOfRoomsAndCapacity(ids, rooms, capacity)
                    .stream()
                    .map(PropertyEntityMapper::toDomain)
                    .toList();
        } else {
            return repository.findByNumberOfRoomsAndCapacity(rooms, capacity)
                    .stream()
                    .map(PropertyEntityMapper::toDomain)
                    .toList();
        }
    }

    @Override
    @Transactional
    public List<Property> findByRooms(int rooms) {
        return repository.findByNumberOfRooms(rooms)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findByCapacity(int capacity) {
        return repository.findByCapacity(capacity)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findByMinRooms(int minRooms) {
        return repository.findByNumberOfRoomsGreaterThanEqual(minRooms)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findByMinCapacity(int minCapacity) {
        return repository.findByCapacityGreaterThanEqual(minCapacity)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findByMinRoomsAndCapacity(int minRooms, int minCapacity) {
        return repository.findByNumberOfRoomsGreaterThanEqualAndCapacityGreaterThanEqual(minRooms, minCapacity)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findByCountryAndIds(String country, List<UUID> ids) {
        return repository.findByCountryAndIdIn(country, ids)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findByCountryAndMinRoomsAndCapacity(String country, List<UUID> ids, int minRooms, int minCapacity) {
        return repository.findByCountryAndIdInAndNumberOfRoomsGreaterThanEqualAndCapacityGreaterThanEqual(
                        country, ids, minRooms, minCapacity)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findByCountryAndTypeAndRoomsAndCapacity(String country, List<UUID> ids, int rooms, int capacity, PropertyType type) {
        return repository.findByCountryAndIdInAndPropertyTypeAndNumberOfRoomsAndCapacity(
                        country, ids, type, rooms, capacity)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public List<Property> findAvailableByType(PropertyType type) {
        return repository.findByIsAvailableTrueAndPropertyType(type)
                .stream()
                .map(PropertyEntityMapper::toDomain)
                .toList();
    }

}
