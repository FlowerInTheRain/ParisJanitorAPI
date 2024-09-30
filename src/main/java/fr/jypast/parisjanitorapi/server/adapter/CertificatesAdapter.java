package fr.jypast.parisjanitorapi.server.adapter;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Certificates;
import fr.jypast.parisjanitorapi.domain.port.out.CertificatesPersistenceSpi;
import fr.jypast.parisjanitorapi.server.entity.ProvidersHabilitationsEntity;
import fr.jypast.parisjanitorapi.server.mapper.ProviderHabilitationsMapper;
import fr.jypast.parisjanitorapi.server.mapper.ProvidersMapper;
import fr.jypast.parisjanitorapi.server.repository.CertificatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CertificatesAdapter implements CertificatesPersistenceSpi {
	private final CertificatesRepository certificatesRepository;
	
	@Transactional
	@Override
	public void saveCertificate(Certificates certificate) {
		var provider = ProvidersMapper.toEntity(certificate.getProvider());
		ProvidersHabilitationsEntity toSave = ProviderHabilitationsMapper.toEntity(certificate, provider);
		certificatesRepository.save(toSave);
	}
}
