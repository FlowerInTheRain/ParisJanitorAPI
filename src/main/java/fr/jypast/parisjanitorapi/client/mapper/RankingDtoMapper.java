package fr.jypast.parisjanitorapi.client.mapper;

import fr.jypast.parisjanitorapi.client.dto.rankings.RankingRequest;
import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Rankings;

import java.util.UUID;

public interface RankingDtoMapper {
	static Rankings toDomain(UUID originId, RankingRequest domain) {
		return Rankings.builder()
					   .build()
					   .withOriginId(originId)
					   .withOriginType(domain.originType())
					   .withTargetId(domain.targetId())
					   .withTargetType(domain.targetType())
					   .withRanking(domain.ranking())
					   .withComment(domain.comment());
	}
}
