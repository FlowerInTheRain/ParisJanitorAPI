package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Certificates;
import fr.jypast.parisjanitorapi.server.entity.ProvidersEntity;
import fr.jypast.parisjanitorapi.server.entity.ProvidersHabilitationsEntity;

public interface ProviderHabilitationsMapper {
	
	static ProvidersHabilitationsEntity toEntity(Certificates certificate, ProvidersEntity provider){
		return ProvidersHabilitationsEntity.builder()
					   .id(certificate.getId())
					   .certificateName(certificate.getCertificateName())
					   .approved(certificate.getApproved())
					   .url(certificate.getUrl())
					   .submissionDate(certificate.getSubmissionDate())
					   .validityDate(certificate.getValidityDate())
					   .provider(provider)
					   .build();
	}
}
