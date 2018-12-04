package org.app.service.ejb;
import java.util.Collection;
import java.util.List;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Angajat;

@Path("angajati")
@Stateless @LocalBean
public class AngajatServiceEJB implements AngajatService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;

	@POST @Path("/add/")
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Angajat addAngajat(Angajat angajatToAdd) {
		em.persist(angajatToAdd);
		em.flush();
		em.refresh(angajatToAdd);
		return angajatToAdd;
	}
	
	@DELETE @Path("/delete/{id}")
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String removeAngajat(@PathParam("id") Integer id) {
		TypedQuery<Angajat> query =  em.createQuery("SELECT c FROM Angajat c WHERE c.idAngajat = :id ", Angajat.class);
		query.setParameter("id", id);
		List<Angajat> angajati = (List<Angajat>) query.getResultList();
		Angajat angajatToDelete = angajati.stream().findFirst().get();
		
		angajatToDelete = em.merge(angajatToDelete);
		em.remove(angajatToDelete);
		em.flush();
		return "True";
	}
	
	@GET @Path("/adresa/{address}")
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Angajat getAngajatByAngajatAdresa(@PathParam("address") String adresa) {
		TypedQuery<Angajat> query =  em.createQuery("SELECT c FROM Angajat c WHERE c.adresa = :adresa ", Angajat.class);
		query.setParameter("adresa", adresa);
		List<Angajat> angajati = (List<Angajat>) query.getResultList();
		return angajati.stream().findFirst().get();
	}
	
	@Override
	@GET @Path("/all")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Angajat> getAngajati() {
		List<Angajat> angajati = em.createQuery("SELECT c FROM Angajat c", Angajat.class).getResultList();
		return angajati;
	}

	@GET @Path("/nume/{name}")
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Angajat getAngajatByNume(@PathParam("name") String angajatNume) {
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
