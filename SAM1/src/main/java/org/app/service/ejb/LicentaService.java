package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Licenta;

@Remote
public interface LicentaService {
	// CREATE or UPDATE
	Licenta addLicenta(Licenta licentaToAdd);
	
	// DELETE
	String removeLicenta(Licenta licentaToDelete);
	
	// READ
	Licenta getLicentaByLicentaID(Integer licentaID);
	Collection<Licenta> getLicente();
	
	// Custom READ: custom query
	Collection<Licenta> getLicenteByTipLicenta(String tipLicenta);
	
	// Others
	String sayRest();
}
