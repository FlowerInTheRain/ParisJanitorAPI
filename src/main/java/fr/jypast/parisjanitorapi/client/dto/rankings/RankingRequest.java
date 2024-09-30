package fr.jypast.parisjanitorapi.client.dto.rankings;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.OriginType;
import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.TargetType;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record RankingRequest(
		OriginType originType,
		UUID targetId,
		TargetType targetType,
		Integer ranking,
		String comment
) {
}
