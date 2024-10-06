package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.domain.functionnal.model.booking.BookingStatus;
import fr.jypast.parisjanitorapi.server.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

    @Query("SELECT b.propertyId FROM BookingEntity b WHERE b.startDate <= :endDate AND b.endDate >= :startDate")
    List<UUID> findBookedPropertyIdsBetweenDates(Date startDate, Date endDate);
    List<BookingEntity> findByPropertyIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(UUID propertyId, Date endDate, Date startDate);
    List<BookingEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date endDate, Date startDate);
    List<BookingEntity> findByPropertyIdAndStartDateBeforeAndEndDateAfter(UUID propertyId, Date endDate, Date startDate);
    List<BookingEntity> findByStartDateBeforeAndEndDateAfter(Date endDate, Date startDate);
    List<BookingEntity> findByTenantIdAndStartDateBeforeAndEndDateAfter(UUID tenantId, Date endDate, Date startDate);
    List<BookingEntity> findByTenantIdAndEndDateBefore(UUID tenantId, Date date);
    List<BookingEntity> findByPropertyIdAndEndDateBefore(UUID propertyId, Date date);
    List<BookingEntity> findByTenantIdAndStartDateAfter(UUID tenantId, Date date);
    List<BookingEntity> findByTenantIdAndStatus(UUID tenantId, BookingStatus status);
    List<BookingEntity> findByTenantIdAndStatusIn(UUID tenantId, List<BookingStatus> statuses);

}
