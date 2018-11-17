package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Furnizor;

@Remote
public interface FurnizorService {
	// CREATE or UPDATE
	Furnizor addFurnizor(Furnizor furnizorToAdd);
	
	// DELETE
	String removeFurnizor(Furnizor furnizorToDelete);
	
	// READ
	Furnizor getFurnizorByFurnizorID(Integer furnizorID);
	Collection<Furnizor> getFurnizori();
	
	// Custom READ: custom query
	Collection<Furnizor> getFurnizoriByFurnizorAdresa(String furnizorAdresa);
	
	// Others
	String sayRest();
}
