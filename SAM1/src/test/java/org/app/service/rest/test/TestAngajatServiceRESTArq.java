package org.app.service.rest.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.AngajatService;
import org.app.service.ejb.AngajatServiceEJB;
import org.app.service.ejb.DataService;
import org.app.service.ejb.test.TestAngajatServiceEJBArq;
import org.app.service.entities.Angajat;
import org.app.service.entities.EntityBase;
import org.app.service.rest.ApplicationConfig;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
public class TestAngajatServiceRESTArq {
	private static Logger logger = 
			Logger.getLogger(TestAngajatServiceRESTArq.class.getName());
	private static String serviceURL = "http://localhost:8080/SAM1/data/angajati";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}	
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-test.war")
	                .addPackage(EntityRepository.class.getPackage())
	                .addPackage(DataService.class.getPackage())
	                .addPackage(EntityBase.class.getPackage())
	                .addPackage(ApplicationConfig.class.getPackage())
	                .addPackage(AngajatService.class.getPackage())
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	
	private Collection<Angajat> testareAll() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/all");
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        Collection<Angajat> value = response.readEntity(new GenericType<Collection<Angajat>>(){});
        response.close();
        
        String str = "REST Response >>>>>>>>> " + value.toString();
        System.out.println(str);
        
        assertTrue("Verificare size collection<angajat>.", value.size() > 0);
        
        return value;
	}
	
	private void testareGetNume() {
		Collection<Angajat> angajati = testareAll();
		Client client = ClientBuilder.newClient();
		Angajat angajatToFind = angajati.stream().findFirst().get();
		WebTarget target = client.target(serviceURL + "/nume/" + angajatToFind.getNume());
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        Angajat value = response.readEntity(new GenericType<Angajat>(){});
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
        
        assertTrue("Verificare get nume.", value.getNume().equals(angajatToFind.getNume()));
	}
	
	private void testareGetAdresa() {
		Collection<Angajat> angajati = testareAll();
		Client client = ClientBuilder.newClient();
		Angajat angajatToFind = angajati.stream().findFirst().get();
		WebTarget target = client.target(serviceURL + "/adresa/" + angajatToFind.getAdresa());
		target.request().accept(MediaType.APPLICATION_XML);
        Response response = target.request().get();
        Angajat value = response.readEntity(new GenericType<Angajat>(){});
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
        
        assertTrue("Verificare get adresa.", value.getAdresa().equals(angajatToFind.getAdresa()));
	}
	
	private void testareRemoveAngajat() {
		Collection<Angajat> angajati = testareAll();
		Client client = ClientBuilder.newClient();
		Angajat angajatToRemove = angajati.stream().findFirst().get();
		WebTarget target = client.target(serviceURL + "/delete/" + angajatToRemove.getIdAngajat());
		target.request().accept(MediaType.APPLICATION_XML);
		
        Response response = target.request().delete();
        String value = response.readEntity(String.class);
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
        
        assertTrue("Verificare remove angajat", true);
	}
	
	private void testareAddAngajat() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serviceURL + "/add/");
		target.request().accept(MediaType.APPLICATION_XML);
		Angajat angajat = new Angajat("nume", "adresa", "telefon", "mail");
        Response response = target.request().post(Entity.entity(angajat, MediaType.APPLICATION_XML));
        Angajat value = response.readEntity(new GenericType<Angajat>(){});
        response.close(); 
        
        System.out.println("REST Response >>>>>>>>> " + value);
        
        assertTrue("Verificare add Angajat.", value.getAdresa().equals("adresa"));
	}
	
	@Test
	public void testAddAngajat() {
		testareAddAngajat();
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

}
