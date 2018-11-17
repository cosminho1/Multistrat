package org.app.service.ejb;
import java.util.Collection;
import java.util.List;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.app.service.entities.Angajat;

@Stateless @LocalBean
public class AngajatServiceEJB implements AngajatService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;

	@Override
	public Angajat addAngajat(Angajat angajatToAdd) {
		em.persist(angajatToAdd);
		em.flush();
		em.refresh(angajatToAdd);
		return angajatToAdd;
	}

	@Override
	public String removeAngajat(Angajat angajatToDelete) {
		angajatToDelete = em.merge(angajatToDelete);
		em.remove(angajatToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Angajat getAngajatByAngajatAdresa(String adresa) {
		TypedQuery<Angajat> query =  em.createQuery("SELECT c FROM Angajat c WHERE c.adresa = :adresa ", Angajat.class);
		query.setParameter("adresa", adresa);
		List<Angajat> angajati = (List<Angajat>) query.getResultList();
		return angajati.stream().findFirst().get();
	}

	@Override
	public Collection<Angajat> getAngajati() {
		List<Angajat> angajati = em.createQuery("SELECT c FROM Angajat c", Angajat.class).getResultList();
		return angajati;
	}

	@Override
	public Angajat getAngajatByNume(String angajatNume) {
		TypedQuery<Angajat> query =  em.createQuery("SELECT c FROM Angajat c WHERE c.nume = :angajatNume ", Angajat.class);
		query.setParameter("angajatNume", angajatNume);
		List<Angajat> angajati = (List<Angajat>) query.getResultList();
		return angajati.stream().findFirst().get();
	}

	@Override
	public String sayRest() {
		return "Angajat Service is On...";
	}


}
