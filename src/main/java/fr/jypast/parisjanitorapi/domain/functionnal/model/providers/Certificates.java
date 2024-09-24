package fr.jypast.parisjanitorapi.domain.functionnal.model.providers;

import fr.jypast.parisjanitorapi.server.entity.ProvidersEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.sql.Date;
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
