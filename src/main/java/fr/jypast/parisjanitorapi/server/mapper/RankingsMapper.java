package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Rankings;
import fr.jypast.parisjanitorapi.server.entity.RankingsEntity;

public interface RankingsMapper {
	static RankingsEntity toEntity(Rankings rankings){
		return RankingsEntity.builder()
					   .originId(rankings.getOriginId())
					   .originType(rankings.getOriginType().name())
					   .targetId(rankings.getTargetId())
					   .targetType(rankings.getTargetType().name())
					   .ranking(rankings.getRanking())
					   .comment(rankings.getComment())
					   .build();
	}
}
