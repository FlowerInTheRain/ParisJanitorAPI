package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.providers.Certificates;

public interface CertificatesPersistenceSpi {
	void saveCertificate(Certificates id);
}
