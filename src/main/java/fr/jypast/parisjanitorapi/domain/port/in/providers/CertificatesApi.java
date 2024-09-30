package fr.jypast.parisjanitorapi.domain.port.in.providers;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Certificates;

public interface CertificatesApi {
	void registerCertificate(Certificates certificate);
}
