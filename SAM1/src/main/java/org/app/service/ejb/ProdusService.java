package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Produs;

@Remote
public interface ProdusService {
	// CREATE or UPDATE
	Produs addProdus(Produs produsToAdd);
	
	// DELETE
	String removeProdus(Produs produsToDelete);
	
	// READ
	Produs getProdusByID(Integer idProdus);
	Collection<Produs> getProduse();
	
	// Custom READ: custom query
	Collection<Produs> getProduseByLicentaTipLicenta(String tipLicenta);
	
	// Others
	String sayRest();
}
