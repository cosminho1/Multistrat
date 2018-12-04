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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.service.entities.Angajat;
import org.app.service.entities.Client;

@Path("clienti")
@Stateless @LocalBean
public class ClientServiceEJB implements ClientService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	@POST @Path("/add/")
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Client addClient(Client clientToAdd) {
		em.persist(clientToAdd);
		em.flush();
		em.refresh(clientToAdd);
		return clientToAdd;
	}

	@DELETE @Path("/delete/{id}")
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String removeClient(@PathParam("id") Integer id) {
		TypedQuery<Client> query =  em.createQuery("SELECT c FROM Client c WHERE c.idClient = :id ", Client.class);
		query.setParameter("id", id);
		List<Client> angajati = (List<Client>) query.getResultList();
		Client clientToDelete = angajati.stream().findFirst().get();
		
		clientToDelete = em.merge(clientToDelete);
		em.remove(clientToDelete);
		em.flush();
		return "True";
	}

	@GET @Path("/adresa/{address}")
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Client getClientByClientAdresa(@PathParam("address") String adresa) {
		TypedQuery<Client> query =  em.createQuery("SELECT c FROM Client c WHERE c.adresa = :adresa ", Client.class);
		query.setParameter("adresa", adresa);
		List<Client> clients = (List<Client>) query.getResultList();
		return clients.stream().findFirst().get();
	}

	@GET @Path("/all")
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Client> getClients() {
		List<Client> clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
		return clients;
	}

	@GET @Path("/all/adresa/{address}")
	@Override
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Client> getClientsByAdresa(@PathParam("address") String adresa) {
		TypedQuery<Client> query =  em.createQuery("SELECT c FROM Client c WHERE c.adresa = :adresa ", Client.class);
		query.setParameter("adresa", adresa);
		List<Client> clients = (List<Client>) query.getResultList();
		return clients;
	}

	@Override
	public String sayRest() {
		return "Client Service is On...";
	}
}
