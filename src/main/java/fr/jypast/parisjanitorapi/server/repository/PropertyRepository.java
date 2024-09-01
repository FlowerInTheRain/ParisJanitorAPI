package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.server.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PropertyRepository extends JpaRepository<PropertyEntity, UUID> {

}
