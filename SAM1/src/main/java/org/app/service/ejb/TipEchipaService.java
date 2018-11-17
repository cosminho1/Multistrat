package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.TipEchipa;

@Remote
public interface TipEchipaService {
	// CREATE or UPDATE
	TipEchipa addTipEchipa(TipEchipa tipEchipaToAdd);
	
	// DELETE
	String removeTipEchipa(TipEchipa tipEchipaToDelete);
	
	// READ
	TipEchipa getTipEchipaByID(Integer idTipEchipa);
	Collection<TipEchipa> getTipEchipe();
	
	// Custom READ: custom query
	Collection<TipEchipa> getTipEchipeByDepartament(String departament);
	
	// Others
	String sayRest();
}
