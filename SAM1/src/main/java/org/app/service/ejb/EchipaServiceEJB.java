package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.app.service.entities.Echipa;

@Stateless @LocalBean
public class EchipaServiceEJB implements EchipaService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	@Override
	public Echipa addEchipa(Echipa echipaToAdd) {
		em.persist(echipaToAdd);
		em.flush();
		em.refresh(echipaToAdd);
		return echipaToAdd;
	}

	@Override
	public String removeEchipa(Echipa echipaToDelete) {
		echipaToDelete = em.merge(echipaToDelete);
		em.remove(echipaToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Echipa getEchipaByEchipaID(Integer echipaID) {
		TypedQuery<Echipa> query =  em.createQuery("SELECT c FROM Echipa c WHERE c.idEchipa = :echipaID ", Echipa.class);
		query.setParameter("echipaID", echipaID);
		List<Echipa> echipe = (List<Echipa>) query.getResultList();
		return echipe.stream().findFirst().get();
	}

	@Override
	public Collection<Echipa> getEchipe() {
		List<Echipa> echipe = em.createQuery("SELECT c FROM Echipa c", Echipa.class).getResultList();
		return echipe;
	}

	@Override
	public Collection<Echipa> getEchipeByTipEchipaID(Integer tipEchipaID) {
		TypedQuery<Echipa> query =  em.createQuery("SELECT c FROM Echipa c WHERE c.tipEchipa.idTipEchipa = :tipEchipaID ", Echipa.class);
		query.setParameter("tipEchipaID", tipEchipaID);
		List<Echipa> echipe = (List<Echipa>) query.getResultList();
		return echipe;
	}

	@Override
	public String sayRest() {
		return "Echipa Service is On...";
	}

}
