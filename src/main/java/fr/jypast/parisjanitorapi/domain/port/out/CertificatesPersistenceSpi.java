package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Certificates;

public interface CertificatesPersistenceSpi {
	void saveCertificate(Certificates id);
}
