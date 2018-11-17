package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.app.service.entities.Licenta;

@Stateless @LocalBean
public class LicentaServiceEJB implements LicentaService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;

	@Override
	public Licenta addLicenta(Licenta licentaToAdd) {
		em.persist(licentaToAdd);
		em.flush();
		em.refresh(licentaToAdd);
		return licentaToAdd;
	}

	@Override
	public String removeLicenta(Licenta licentaToDelete) {
		licentaToDelete = em.merge(licentaToDelete);
		em.remove(licentaToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Licenta getLicentaByLicentaID(Integer licentaID) {
		TypedQuery<Licenta> query =  em.createQuery("SELECT c FROM Licenta c WHERE c.idLicenta = :licentaID ", Licenta.class);
		query.setParameter("licentaID", licentaID);
		List<Licenta> licente = (List<Licenta>) query.getResultList();
		return licente.stream().findFirst().get();
	}

	@Override
	public Collection<Licenta> getLicente() {
		List<Licenta> licente = em.createQuery("SELECT c FROM Licenta c", Licenta.class).getResultList();
		return licente;
	}

	@Override
	public Collection<Licenta> getLicenteByTipLicenta(String tipLicenta) {
		TypedQuery<Licenta> query =  em.createQuery("SELECT c FROM Licenta c WHERE c.tipLicenta = :tipLicenta ", Licenta.class);
		query.setParameter("tipLicenta", tipLicenta);
		List<Licenta> licente = (List<Licenta>) query.getResultList();
		return licente;
	}

	@Override
	public String sayRest() {
		return "Licenta Service is On...";
	}

}
