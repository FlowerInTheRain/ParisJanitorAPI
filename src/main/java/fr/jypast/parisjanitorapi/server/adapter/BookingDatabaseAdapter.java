package fr.jypast.parisjanitorapi.server.adapter;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import fr.jypast.parisjanitorapi.server.entity.BookingEntity;
import fr.jypast.parisjanitorapi.server.mapper.BookingEntityMapper;
import fr.jypast.parisjanitorapi.server.repository.BookingRepository;
import fr.jypast.parisjanitorapi.server.repository.PropertyRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class BookingDatabaseAdapter implements BookingPersistenceSpi {

    private final BookingRepository repository;
    private final PropertyRepository propertyRepository;

    @Override
    @Transactional
    public Either<ApplicationError, Booking> save(Booking o) {
        return Try(() -> repository.save(BookingEntityMapper.fromDomain(o)))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Erreur lors de l'écriture en BDD", null, o, throwable))
                .map(BookingEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public List<Booking> findAll() {
        return repository.findAll().stream().map(BookingEntityMapper::toDomain).toList();
    }

    @Override
    public List<Booking> findPropertiesNotIn(List<UUID> propertyIds) {
        return repository.findAll()
                .stream()
                .filter(booking -> !propertyIds.contains(booking.getPropertyId()))
                .map(BookingEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return repository.findById(id).map(BookingEntityMapper::toDomain);
    }


    @Override
    public List<Booking> findByPropertyIdAndDates(UUID propertyId, LocalDate startDate, LocalDate endDate) {
        return repository.findByPropertyIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(propertyId, endDate, startDate)
                .stream()
                .map(BookingEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<UUID> findBookedPropertyIdsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return repository.findBookedPropertyIdsBetweenDates(startDate, endDate);
    }

    @Override
    public List<UUID> findUnavailablePropertyIdsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return repository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(endDate, startDate)
                .stream()
                .map(BookingEntity::getPropertyId)
                .collect(Collectors.toList());
    }


}
