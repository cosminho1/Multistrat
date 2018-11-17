package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Suport;

@Remote
public interface SuportService {
	// CREATE or UPDATE
	Suport addSuport(Suport suportToAdd);
	
	// DELETE
	String removeSuport(Suport suportToDelete);
	
	// READ
	Suport getSuportByID(Integer idSuport);
	Collection<Suport> getSuports();
	
	// Custom READ: custom query
	Collection<Suport> getSupportsByTipEchipaDepartament(String departament);
	
	// Others
	String sayRest();
}
