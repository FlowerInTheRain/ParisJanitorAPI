package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.server.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PropertyRepository extends JpaRepository<PropertyEntity, UUID> {

    @Query("SELECT p.id FROM PropertyEntity p")
    List<UUID> findAllPropertyIds();
    @Query("SELECT p.id FROM PropertyEntity p WHERE p.id NOT IN (:bookedPropertyIds)")
    List<UUID> findAvailablePropertiesByExcludingIds(List<UUID> bookedPropertyIds);
    List<PropertyEntity> findByIdInAndSizeGreaterThanEqual(List<UUID> ids, double minSize);
    List<PropertyEntity> findByIdInAndSizeLessThanEqual(List<UUID> ids, double maxSize);
    List<PropertyEntity> findBySizeBetween(double minSize, double maxSize);
    List<PropertyEntity> findByCountry(String country);



}
