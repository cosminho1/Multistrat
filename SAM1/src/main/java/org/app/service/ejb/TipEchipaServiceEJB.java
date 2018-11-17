package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.app.service.entities.TipEchipa;

@Stateless @LocalBean
public class TipEchipaServiceEJB implements TipEchipaService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	@Override
	public TipEchipa addTipEchipa(TipEchipa tipEchipaToAdd) {
		em.persist(tipEchipaToAdd);
		em.flush();
		em.refresh(tipEchipaToAdd);
		return tipEchipaToAdd;
	}

	@Override
	public String removeTipEchipa(TipEchipa tipEchipaToDelete) {
		tipEchipaToDelete = em.merge(tipEchipaToDelete);
		em.remove(tipEchipaToDelete);
		em.flush();
		return "True";
	}

	@Override
	public TipEchipa getTipEchipaByID(Integer idTipEchipa) {
		TypedQuery<TipEchipa> query =  em.createQuery("SELECT c FROM TipEchipa c WHERE c.idTipEchipa = :idTipEchipa ", TipEchipa.class);
		query.setParameter("idTipEchipa", idTipEchipa);
		List<TipEchipa> tipEchipe = (List<TipEchipa>) query.getResultList();
		return tipEchipe.stream().findFirst().get();
	}

	@Override
	public Collection<TipEchipa> getTipEchipe() {
		List<TipEchipa> tipEchipe = em.createQuery("SELECT c FROM TipEchipa c", TipEchipa.class).getResultList();
		return tipEchipe;
	}

	@Override
	public Collection<TipEchipa> getTipEchipeByDepartament(String departament) {
		TypedQuery<TipEchipa> query =  em.createQuery("SELECT c FROM TipEchipa c WHERE c.departament = :departament ", TipEchipa.class);
		query.setParameter("departament", departament);
		List<TipEchipa> tipEchipe = (List<TipEchipa>) query.getResultList();
		return tipEchipe;
	}

	@Override
	public String sayRest() {
		return "TipEchipa Service is On...";
	}

}
