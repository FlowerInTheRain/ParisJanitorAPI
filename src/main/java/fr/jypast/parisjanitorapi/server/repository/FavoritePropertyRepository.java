package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.server.entity.FavoritePropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoritePropertyRepository extends JpaRepository<FavoritePropertyEntity, UUID> {
    List<FavoritePropertyEntity> findByUserId(UUID userId);
    void deleteByUserIdAndPropertyId(UUID userId, UUID propertyId);
}
