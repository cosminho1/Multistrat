package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.app.service.entities.Garantie;

@Stateless @LocalBean
public class GarantieServiceEJB implements GarantieService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	@Override
	public Garantie addGarantie(Garantie garantieToAdd) {
		em.persist(garantieToAdd);
		em.flush();
		em.refresh(garantieToAdd);
		return garantieToAdd;
	}

	@Override
	public String removeGarantie(Garantie garantieToDelete) {
		garantieToDelete = em.merge(garantieToDelete);
		em.remove(garantieToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Garantie getGarantieByGarantieID(Integer garantieID) {
		TypedQuery<Garantie> query =  em.createQuery("SELECT c FROM Garantie c WHERE c.idGarantie = :garantieID ", Garantie.class);
		query.setParameter("garantieID", garantieID);
		List<Garantie> garantii = (List<Garantie>) query.getResultList();
		return garantii.stream().findFirst().get();
	}

	@Override
	public Collection<Garantie> getGarantii() {
		List<Garantie> garantii = em.createQuery("SELECT c FROM Garantie c", Garantie.class).getResultList();
		return garantii;
	}

	@Override
	public Collection<Garantie> getGarantiiByDurata(String garantieDurata) {
		TypedQuery<Garantie> query =  em.createQuery("SELECT c FROM Garantie c WHERE c.durata = :garantieDurata ", Garantie.class);
		query.setParameter("garantieDurata", garantieDurata);
		List<Garantie> garantii = (List<Garantie>) query.getResultList();
		return garantii;
	}

	@Override
	public String sayRest() {
		return "Garantie Service is On...";
	}

}
