package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Angajat;

@Remote
public interface AngajatService {
	// CREATE or UPDATE
	Angajat addAngajat(Angajat angajatToAdd);
	
	// DELETE
	String removeAngajat(Integer id);
	
	// READ
	Angajat getAngajatByAngajatAdresa(String adresa);
	Collection<Angajat> getAngajati();
	
	// Custom READ: custom query
	Angajat getAngajatByNume(String angajatNume);
	
	// Others
	String sayRest();
}
