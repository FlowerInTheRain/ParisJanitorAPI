package fr.jypast.parisjanitorapi.server.adapter;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.Booking;
import fr.jypast.parisjanitorapi.domain.port.out.BookingPersistenceSpi;
import fr.jypast.parisjanitorapi.server.mapper.BookingEntityMapper;
import fr.jypast.parisjanitorapi.server.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingDatabaseAdapter implements BookingPersistenceSpi {

    private final BookingRepository repository;

    @Override
    public Booking save(Booking booking) {
        return BookingEntityMapper.toDomain(repository.save(BookingEntityMapper.fromDomain(booking)));
    }

    @Override
    public List<Booking> findByPropertyIdAndDates(UUID propertyId, LocalDate startDate, LocalDate endDate) {
        return repository.findByPropertyIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(propertyId, endDate, startDate)
                .stream()
                .map(BookingEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findBookingsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return repository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(endDate, startDate)
                .stream()
                .map(BookingEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findPropertiesNotIn(List<UUID> propertyIds) {
        return repository.findAll()
                .stream()
                .filter(booking -> !propertyIds.contains(booking.getPropertyId()))
                .map(BookingEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
