package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Echipa;

@Remote
public interface EchipaService {
	// CREATE or UPDATE
	Echipa addEchipa(Echipa echipaToAdd);
	
	// DELETE
	String removeEchipa(Echipa echipaToDelete);
	
	// READ
	Echipa getEchipaByEchipaID(Integer echipaID);
	Collection<Echipa> getEchipe();
	
	// Custom READ: custom query
	Collection<Echipa> getEchipeByTipEchipaID(Integer tipEchipaID);
	
	// Others
	String sayRest();
}
