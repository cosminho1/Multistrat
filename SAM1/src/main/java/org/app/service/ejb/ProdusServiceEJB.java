package org.app.service.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.app.service.entities.Licenta;
import org.app.service.entities.Produs;

@Stateless @LocalBean
public class ProdusServiceEJB implements ProdusService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	@Override
	public Produs addProdus(Produs produsToAdd) {
		em.persist(produsToAdd);
		em.flush();
		em.refresh(produsToAdd);
		return produsToAdd;
	}

	@Override
	public String removeProdus(Produs produsToDelete) {
		produsToDelete = em.merge(produsToDelete);
		em.remove(produsToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Produs getProdusByID(Integer idProdus) {
		TypedQuery<Produs> query =  em.createQuery("SELECT c FROM Produs c WHERE c.idProdus = :idProdus ", Produs.class);
		query.setParameter("idProdus", idProdus);
		List<Produs> produse = (List<Produs>) query.getResultList();
		return produse.stream().findFirst().get();
	}

	@Override
	public Collection<Produs> getProduse() {
		List<Produs> produse = em.createQuery("SELECT c FROM Produs c", Produs.class).getResultList();
		return produse;
	}

	@Override
	public Collection<Produs> getProduseByLicentaTipLicenta(String tipLicenta) {
		List<Produs> toateProdusele = (List<Produs>) getProduse();
		List<Produs> produseSelectate = new ArrayList<Produs>();
		for(Produs produs : toateProdusele) {
			List<Licenta> licente = produs.getLicente();
			
			for(Licenta licenta : licente) {
				if (licenta.getTipLicenta().equals(tipLicenta)) {
					produseSelectate.add(produs);
					break;
				}
			}
		}
		
		return produseSelectate;
	}

	@Override
	public String sayRest() {
		return "Produss Service is On...";
	}

}
