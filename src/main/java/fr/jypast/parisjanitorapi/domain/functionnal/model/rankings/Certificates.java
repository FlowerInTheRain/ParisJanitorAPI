package fr.jypast.parisjanitorapi.domain.functionnal.model.rankings;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Date;
import java.time.Instant;

@Value
@Builder
public class Certificates {
	@With
	Integer id;
	
	@With
	String certificateName;
	
	@With
	String url;
	@Builder.Default
	@With
	Boolean approved = false;
	
	@Builder.Default
	@With
	Date submissionDate = new Date(Instant.now().getEpochSecond());
	
	@With
	Date validityDate;
	
	@With
	Providers provider;
}
