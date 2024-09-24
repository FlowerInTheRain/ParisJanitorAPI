package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.server.entity.ProvidersEntity;
import fr.jypast.parisjanitorapi.server.entity.ProvidersHabilitationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificatesRepository extends JpaRepository<ProvidersHabilitationsEntity, Integer> {
	List<ProvidersHabilitationsEntity> findAllByProvider(ProvidersEntity providersEntity);
}
