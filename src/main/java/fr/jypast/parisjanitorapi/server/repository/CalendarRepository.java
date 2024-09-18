package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.server.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CalendarRepository extends JpaRepository<CalendarEntity, UUID> {

    @Query("SELECT DISTINCT c.propertyId FROM CalendarEntity c WHERE c.date BETWEEN :startDate AND :endDate AND c.isAvailable = true")
    List<UUID> findAvailablePropertiesBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<CalendarEntity> findByPropertyId(UUID propertyId);
    List<CalendarEntity> findByPropertyIdAndDateIn(UUID propertyId, List<LocalDate> dates);
    void deleteByPropertyIdAndDateIn(UUID propertyId, List<LocalDate> dates);
}
