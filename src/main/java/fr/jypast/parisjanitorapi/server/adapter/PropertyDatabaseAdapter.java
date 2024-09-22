package fr.jypast.parisjanitorapi.server.adapter;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.property.Property;
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

}
