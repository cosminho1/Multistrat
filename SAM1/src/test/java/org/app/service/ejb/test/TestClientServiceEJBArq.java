package org.app.service.ejb.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.ClientService;
import org.app.service.ejb.ClientServiceEJB;
import org.app.service.entities.Client;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClientServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestClientServiceEJBArq.class.getName());
	@EJB
	private static ClientService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Client.class.getPackage())
				.addClass(ClientService.class)
				.addClass(ClientServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	private void addClients(Integer numberOfClients) {
		for (int i = 1; i <= numberOfClients; i++) {
			String denumire = "Denumire_" + i;
			String telefon = "Telefon_" + i;
			String adresa = "Adresa_" + i;
			String mail = "Mail_" + i;
			String cui = "Cui_" + i;
			
			Client clientToAdd = new Client(denumire, telefon, adresa, mail, cui);
			
			service.addClient(clientToAdd);
		}
	}
	
	@Test
	public void test1_AddClient() {
		logger.info("DEBUG: Junit TESTING: testAddClient ...");
		
		Integer clientsToAdd = 5;
		addClients(5);
		Collection<Client> clients = service.getClients();
		
		assert clients.size() == clientsToAdd;
	}
	
	@Test
	public void test2_GetClients() {
		logger.info("DEBUG: Junit TESTING: testGetClients ...");
		
		Collection<Client> clients = service.getClients();
		
		assertTrue("Fail to read clients!", clients.size() > 0);
	}
	
	@Test
	public void test3_GetClientByDenumire() {
		logger.info("DEBUG: Junit TESTING: GetClientByDenumire ...");
		
		addClients(5);
		
		Client client = service.getClientByClientAdresa("Adresa_2");
		
		assertTrue("Get Client By Adresa Failed!", client.getAdresa().equals("Adresa_2"));
	}
	
	@Test
	public void test4_GetClientByAdresa() {
		logger.info("DEBUG: Junit TESTING: GetClientByAdresa ...");
		
		addClients(5);
		
		Client client = service.getClientByClientAdresa("Adresa_2");
		
		assertTrue("Get Client By ID Failed!", client.getAdresa().equals("Adresa_2"));
	}
	
	@Test
	public void test5_RemoveClient() {
		logger.info("DEBUG: Junit TESTING: testRemoveClient ...");
		
		Collection<Client> clients = service.getClients();
		for (Client c: clients) {
			service.removeClient(c);
		}
		Collection<Client> clientsAfterRemove = service.getClients();
		
		assertTrue("Fail to read clients!", clientsAfterRemove.size() == 0);
	}
}
