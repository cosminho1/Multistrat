package org.app.service.rest.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.app.service.entities.Angajat;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestClientServiceRest {
	private static Logger logger = 
			Logger.getLogger(TestClientServiceRest.class.getName());

	private static String serviceURL = "http://localhost:8080/SAM1/data/clienti";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	private Collection<org.app.service.entities.Client> testareAll() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/all");
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        Collection<org.app.service.entities.Client> value = response.readEntity(new GenericType<Collection<org.app.service.entities.Client>>(){});
        response.close();
        
        String str = "REST Response >>>>>>>>> " + value;
        System.out.println(str);
        
        assertTrue("Verificare size collection<client>.", value.size() > 0);
        
        return value;
  
	}
	
	private void testareGetAdresa() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/adresa/AdresaCeaVeche");
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        org.app.service.entities.Client value = response.readEntity(new GenericType<org.app.service.entities.Client>(){});
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
        
        assertTrue("Verificare get adresa.", value.getAdresa().equals("AdresaCeaVeche"));
	}
	
	private void testareRemoveClient() {
		Collection<org.app.service.entities.Client> clients = testareAll();
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/delete/" + clients.stream().findFirst().get().getIdClient());
		target.request().accept(MediaType.APPLICATION_XML);
		
        Response response = target.request().delete();
        String value = response.readEntity(String.class);
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
        
        assertTrue("Verificare remove client", value.equals("True"));
	}
	
	private void testareGetClientsByAdresa() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/all/adresa/AdresaCeaVeche");
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        Collection<org.app.service.entities.Client> value = response.readEntity(new GenericType<Collection<org.app.service.entities.Client>>(){});
        response.close();
        
        String str = "REST Response >>>>>>>>> " + value;
        System.out.println(str);
        
        for(org.app.service.entities.Client c : value) {
        	assertTrue("Verificare get clients by adresa", c.getAdresa().equals("AdresaCeaVeche"));
        }
	}
	
	private void testareAddClient() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/add/");
		target.request().accept(MediaType.APPLICATION_XML);
		
		String denumire = "ClientNou";
		String telefon = "07456Privat";
		String adresa = "AdresaCeaVeche";
		String mail = "MailulCelNou@gmail.com";
		String cui = "1235677123CUIII";
		
		org.app.service.entities.Client clientToAdd = new org.app.service.entities.Client(
				denumire, telefon, adresa, mail, cui);
        Response response = target.request().post(Entity.entity(clientToAdd, MediaType.APPLICATION_XML));
        org.app.service.entities.Client value = response.readEntity(new GenericType<org.app.service.entities.Client>(){});
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
        
        assertTrue("Verificare add client.", value.getAdresa().equals(adresa));
	}
	
	@Test
	public void testAll() {
		testareAll();
	}
	
	@Test
	public void testGetAdresa() {
		testareGetAdresa();
	}
	
	@Test
	public void testRemoveClient() {
		testareRemoveClient();
	}
	
	@Test
	public void testGetClientsByAdresa() {
		testareGetClientsByAdresa();
	}
	
	@Test
	public void testAddClient() {
		testareAddClient();
	}
}
