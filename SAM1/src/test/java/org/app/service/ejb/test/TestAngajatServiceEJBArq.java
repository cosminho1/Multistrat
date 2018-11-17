package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.AngajatService;
import org.app.service.ejb.AngajatServiceEJB;
import org.app.service.entities.Angajat;
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
public class TestAngajatServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestAngajatServiceEJBArq.class.getName());
	@EJB
	private static AngajatService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Angajat.class.getPackage())
				.addClass(AngajatService.class)
				.addClass(AngajatServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	private void addAngajati(Integer count) {
		for (int i = 1; i <= count; i++) {
			String nume = "Nume_" + i;
			String adresa = "Adresa_" + i;
			String telefon = "Telefon_" + i;
			String mail = "Mail_" + i;
			Angajat angajatToAdd = new Angajat(nume, adresa, telefon, mail);
			
			service.addAngajat(angajatToAdd);
		}
	}
	
	@Test
	public void test1_AddAngajat() {
		logger.info("DEBUG: Junit TESTING: testAddAngajat ...");
		
		Integer angajatiToAdd = 5;
		addAngajati(angajatiToAdd);
		Collection<Angajat> angajati = service.getAngajati();
		
		assert angajati.size() == angajatiToAdd;
	}
	
	@Test
	public void test2_GetAngajati() {
		logger.info("DEBUG: Junit TESTING: testGetAngajati ...");
		
		Collection<Angajat> angajati = service.getAngajati();
		
		assertTrue("Fail to read angajati!", angajati.size() > 0);
	}
	
	@Test
	public void test3_GetAngajatByNume() {
		logger.info("DEBUG: Junit TESTING: GetAngajatByNume ...");
		
		addAngajati(5);
		
		Angajat angajat = service.getAngajatByNume("Nume_2");
		
		assertTrue("Get Angajat By Nume Failed!", angajat.getNume().equals("Nume_2"));
	}
	
	@Test
	public void test4_GetAngajatByAdresa() {
		logger.info("DEBUG: Junit TESTING: GetAngajatByAdresa ...");
		
		addAngajati(5);
		
		Angajat angajat = service.getAngajatByAngajatAdresa("Adresa_2");
		
		assertTrue("Get Angajat By ID Failed!", angajat.getAdresa().equals("Adresa_2"));
	}
	
	@Test
	public void test5_RemoveAngajat() {
		logger.info("DEBUG: Junit TESTING: testRemoveAngajat ...");
		
		Collection<Angajat> angajati = service.getAngajati();
		for (Angajat c: angajati) {
			service.removeAngajat(c);
		}
		Collection<Angajat> angajatiAfterRemove = service.getAngajati();
		
		assertTrue("Fail to read angajati!", angajatiAfterRemove.size() == 0);
	}
}
