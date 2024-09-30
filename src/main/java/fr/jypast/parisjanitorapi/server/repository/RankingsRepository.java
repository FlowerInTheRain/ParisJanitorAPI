package fr.jypast.parisjanitorapi.server.repository;

import fr.jypast.parisjanitorapi.server.entity.RankingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RankingsRepository extends JpaRepository<RankingsEntity, UUID>  {
	@Query("SELECT AVG(R.ranking), count(R.ranking) FROM RankingsEntity as R" +
				   " WHERE R.targetId = :providerId")
	Object[] findRankingsForTarget(UUID providerId);
	
	@Query("SELECT R FROM RankingsEntity as R WHERE R.originId = :originId and R.targetId = :targetId")
	RankingsEntity findByTargetAndOrigin(UUID originId, UUID targetId);
}
