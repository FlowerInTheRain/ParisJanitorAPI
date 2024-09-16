package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.server.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CalendarRepository extends JpaRepository<CalendarEntity, UUID> {
    List<CalendarEntity> findByPropertyId(UUID propertyId);
    List<CalendarEntity> findByPropertyIdAndDateIn(UUID propertyId, List<LocalDate> dates);
    List<UUID> findAvailablePropertiesBetweenDates(LocalDate startDate, LocalDate endDate);
    void deleteByPropertyIdAndDateIn(UUID propertyId, List<LocalDate> dates);
}
