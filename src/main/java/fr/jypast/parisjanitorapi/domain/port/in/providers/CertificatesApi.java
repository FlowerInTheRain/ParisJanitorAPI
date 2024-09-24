package fr.jypast.parisjanitorapi.domain.port.in.providers;

import fr.jypast.parisjanitorapi.domain.functionnal.model.providers.Certificates;

public interface CertificatesApi {
	void registerCertificate(Certificates certificate);
}
