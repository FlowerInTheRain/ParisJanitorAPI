package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.server.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    @Query("SELECT b.propertyId FROM BookingEntity b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<UUID> findBookedPropertyIdsBetweenDates(Date startDate, Date endDate);
    List<BookingEntity> findByPropertyIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(UUID propertyId, Date endDate, Date startDate);
    List<BookingEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date endDate, Date startDate);
}
