package fr.jypast.parisjanitorapi.domain.functionnal.model.providers;

import fr.jypast.parisjanitorapi.server.entity.ReferencedPrestationsEntity;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@Value
@Builder
public class Providers {
	@With
	Integer id;
	
	@With
	String fullName;
	
	@With
	String email;
	
	@With
	String phoneNumber;
	
	@With
	String joinDate;
	
	@With
	Boolean joinRequestStatus;
	
	@With
	String joinRequestMessage;
	
}
