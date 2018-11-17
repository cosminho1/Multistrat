package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Garantie;

@Remote
public interface GarantieService {
	// CREATE or UPDATE
	Garantie addGarantie(Garantie garantieToAdd);
	
	// DELETE
	String removeGarantie(Garantie garantieToDelete);
	
	// READ
	Garantie getGarantieByGarantieID(Integer garantieID);
	Collection<Garantie> getGarantii();
	
	// Custom READ: custom query
	Collection<Garantie> getGarantiiByDurata(String garantieDurata);
	
	// Others
	String sayRest();
}
