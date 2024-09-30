package fr.jypast.parisjanitorapi.domain.functionnal.model.rankings;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class Rankings {
	@With
	UUID originId;
	
	@With
	OriginType originType;
	
	@With
	UUID targetId;
	
	@With
	TargetType targetType;
	
	@With
	Integer ranking;
	
	@With
	String comment;
}
