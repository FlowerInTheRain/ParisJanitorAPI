package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.server.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CalendarRepository extends JpaRepository<CalendarEntity, UUID> {

    // Custom query to find available properties between two dates
    @Query("SELECT DISTINCT c.propertyId FROM CalendarEntity c WHERE c.date BETWEEN :startDate AND :endDate AND c.isAvailable = true")
    List<UUID> findAvailablePropertiesBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    // Other existing methods
    List<CalendarEntity> findByPropertyId(UUID propertyId);
    List<CalendarEntity> findByPropertyIdAndDateIn(UUID propertyId, List<Date> dates);
    void deleteByPropertyIdAndDateIn(UUID propertyId, List<Date> dates);
}
