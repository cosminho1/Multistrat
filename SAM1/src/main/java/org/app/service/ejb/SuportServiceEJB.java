package org.app.service.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.app.service.entities.Suport;
import org.app.service.entities.TipEchipa;

@Stateless @LocalBean
public class SuportServiceEJB implements SuportService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	@Override
	public Suport addSuport(Suport suportToAdd) {
		em.persist(suportToAdd);
		em.flush();
		em.refresh(suportToAdd);
		return suportToAdd;
	}

	@Override
	public String removeSuport(Suport suportToDelete) {
		suportToDelete = em.merge(suportToDelete);
		em.remove(suportToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Suport getSuportByID(Integer idSuport) {
		TypedQuery<Suport> query =  em.createQuery("SELECT c FROM Suport c WHERE c.idSuport = :idSuport ", Suport.class);
		query.setParameter("idSuport", idSuport);
		List<Suport> supports = (List<Suport>) query.getResultList();
		return supports.stream().findFirst().get();
	}

	@Override
	public Collection<Suport> getSuports() {
		List<Suport> supports = em.createQuery("SELECT c FROM Suport c", Suport.class).getResultList();
		return supports;
	}

	@Override
	public Collection<Suport> getSupportsByTipEchipaDepartament(String departament) {
		List<Suport> allSuports = (List<Suport>) getSuports();
		List<Suport> selectedSuports = new ArrayList<Suport>();
		
		for (Suport suport : allSuports) {
			TipEchipa tipEchipa = suport.getTipEchipa();
			if (tipEchipa.getDepartament().equals(departament)) {
				selectedSuports.add(suport);
				continue;
			}
		}
		return selectedSuports;
	}

	@Override
	public String sayRest() {
		return "Suport Service is On...";
	}

}
