package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.app.service.entities.Client;

@Stateless @LocalBean
public class ClientServiceEJB implements ClientService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;

	@Override
	public Client addClient(Client clientToAdd) {
		em.persist(clientToAdd);
		em.flush();
		em.refresh(clientToAdd);
		return clientToAdd;
	}

	@Override
	public String removeClient(Client clientToDelete) {
		clientToDelete = em.merge(clientToDelete);
		em.remove(clientToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Client getClientByClientAdresa(String adresa) {
		TypedQuery<Client> query =  em.createQuery("SELECT c FROM Client c WHERE c.adresa = :adresa ", Client.class);
		query.setParameter("adresa", adresa);
		List<Client> clients = (List<Client>) query.getResultList();
		return clients.stream().findFirst().get();
	}

	@Override
	public Collection<Client> getClients() {
		List<Client> clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
		return clients;
	}

	@Override
	public Collection<Client> getClientsByAdresa(String adresa) {
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
