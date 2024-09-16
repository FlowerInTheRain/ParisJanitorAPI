package fr.jypast.parisjanitorapi.server.adapter;

import fr.jypast.parisjanitorapi.domain.ApplicationError;
import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.OccupancyCalendar;
import fr.jypast.parisjanitorapi.domain.port.out.CalendarPersistenceSpi;
import fr.jypast.parisjanitorapi.server.mapper.CalendarEntityMapper;
import fr.jypast.parisjanitorapi.server.repository.CalendarRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.vavr.API.Try;

@Component
@RequiredArgsConstructor
public class CalendarDatabaseAdapter implements CalendarPersistenceSpi {

    private final CalendarRepository repository;

    @Override
    public List<OccupancyCalendar> findByPropertyId(UUID propertyId) {
        return repository.findByPropertyId(propertyId)
                .stream()
                .map(CalendarEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OccupancyCalendar> findByPropertyIdAndDates(UUID propertyId, List<LocalDate> dates) {
        return repository.findByPropertyIdAndDateIn(propertyId, dates)
                .stream()
                .map(CalendarEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Either<ApplicationError, OccupancyCalendar> save(OccupancyCalendar occupancyCalendar) {
        return Try(() -> repository.save(CalendarEntityMapper.fromDomain(occupancyCalendar)))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Error saving calendar entry", "Additional details or source", occupancyCalendar, throwable))
                .map(CalendarEntityMapper::toDomain);
    }

    @Override
    public List<UUID> findAvailablePropertiesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return repository.findAvailablePropertiesBetweenDates(startDate, endDate);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<OccupancyCalendar> findAll() {
        return repository.findAll()
                .stream()
                .map(CalendarEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OccupancyCalendar> findById(UUID id) {
        return repository.findById(id).map(CalendarEntityMapper::toDomain);
    }

    @Override
    public void deleteByPropertyIdAndDates(UUID propertyId, List<LocalDate> dates) {
        repository.deleteByPropertyIdAndDateIn(propertyId, dates);
    }
}
