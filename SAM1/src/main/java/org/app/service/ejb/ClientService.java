package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Client;

@Remote
public interface ClientService {
	// CREATE or UPDATE
	Client addClient(Client clientToAdd);
	
	// DELETE
	String removeClient(Client clientToDelete);
	
	// READ
	Client getClientByClientAdresa(String adresa);
	Collection<Client> getClients();
	
	// Custom READ: custom query
	Collection<Client> getClientsByAdresa(String adresa);
	
	// Others
	String sayRest();
}
