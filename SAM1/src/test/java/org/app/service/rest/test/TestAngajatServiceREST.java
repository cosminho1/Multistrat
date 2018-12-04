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

import org.app.service.ejb.test.TestAngajatServiceEJBArq;
import org.app.service.entities.Angajat;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAngajatServiceREST {
	private static Logger logger = 
			Logger.getLogger(TestAngajatServiceREST.class.getName());

	private static String serviceURL = "http://localhost:8080/SAM1/data/angajati";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	private void testareAll() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/all");
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        response.close();
        
        String str = "REST Response >>>>>>>>> " + value;
        System.out.println(str);
	}
	
	private void testareGetNume() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/nume/Nume_25");
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
	}
	
	private void testareGetAdresa() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/adresa/Adresa_25");
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
	}
	
	private void testareRemoveAngajat() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/delete/8905");
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
	}
	
	private void testareAddAngajat() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/add/");
		target.request().accept(MediaType.APPLICATION_XML);
		Angajat angajat = new Angajat("nume", "adresa", "telefon", "mail");
        Response response = target.request().post(Entity.entity(angajat, MediaType.APPLICATION_XML));
        String value = response.readEntity(String.class);
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
	}
	
	@Test
	public void testAll() {
		testareAll();
	}
	
	@Test
	public void testGetNume() {
		testareGetNume();
	}
	
	@Test
	public void testGetAdresa() {
		testareGetAdresa();
	}
	
	@Test
	public void testRemoveAngajat() {
		testareRemoveAngajat();
	}
	
	@Test
	public void testAddAngajat() {
		testareAddAngajat();
	}
}
