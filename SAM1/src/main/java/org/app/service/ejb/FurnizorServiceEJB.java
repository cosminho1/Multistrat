package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.app.service.entities.Furnizor;

@Stateless @LocalBean
public class FurnizorServiceEJB implements FurnizorService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	@Override
	public Furnizor addFurnizor(Furnizor furnizorToAdd) {
		em.persist(furnizorToAdd);
		em.flush();
		em.refresh(furnizorToAdd);
		return furnizorToAdd;
	}

	@Override
	public String removeFurnizor(Furnizor furnizorToDelete) {
		furnizorToDelete = em.merge(furnizorToDelete);
		em.remove(furnizorToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Furnizor getFurnizorByFurnizorID(Integer furnizorID) {
		TypedQuery<Furnizor> query =  em.createQuery("SELECT c FROM Furnizor c WHERE c.idFurnizor = :furnizorID ", Furnizor.class);
		query.setParameter("furnizorID", furnizorID);
		List<Furnizor> furnizori = (List<Furnizor>) query.getResultList();
		return furnizori.stream().findFirst().get();
	}

	@Override
	public Collection<Furnizor> getFurnizori() {
		List<Furnizor> furnizori = em.createQuery("SELECT c FROM Furnizor c", Furnizor.class).getResultList();
		return furnizori;
	}

	@Override
	public Collection<Furnizor> getFurnizoriByFurnizorAdresa(String furnizorAdresa) {
		TypedQuery<Furnizor> query =  em.createQuery("SELECT c FROM Furnizor c WHERE c.adresa = :furnizorAdresa ", Furnizor.class);
		query.setParameter("furnizorAdresa", furnizorAdresa);
		List<Furnizor> furnizori = (List<Furnizor>) query.getResultList();
		return furnizori;
	}

	@Override
	public String sayRest() {
		return "Furnizor Service is On...";
	}

}
