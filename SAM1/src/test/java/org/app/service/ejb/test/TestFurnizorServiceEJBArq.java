package org.app.service.ejb.test;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.FurnizorService;
import org.app.service.ejb.FurnizorServiceEJB;
import org.app.service.entities.Furnizor;
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
public class TestFurnizorServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestFurnizorServiceEJBArq.class.getName());
	@EJB
	private static FurnizorService service;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Furnizor.class.getPackage())
				.addClass(FurnizorService.class)
				.addClass(FurnizorServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	private void addFurnizori(Integer numarFurnizori) {
		for (int i = 1; i <= numarFurnizori; i++) {
			String denumire = "Denumire_" + i;
			String telefon = "Telefon_" + i;
			String adresa = "Adresa_" + i;
			String mail = "Mail_" + i;
			String cui = "Cui_" + i;
			Furnizor furnizor = new Furnizor(denumire, telefon, adresa, mail, cui);
			service.addFurnizor(furnizor);
		}
	}
	
	@Test
	public void test1_AddFurnizori() {
		logger.info("DEBUG: Junit TESTING: AddFurnizori ...");
		Integer numarFurnizori = 5;
		addFurnizori(numarFurnizori);
		Collection<Furnizor> furnizori = service.getFurnizori();
		
		assert furnizori.size() == numarFurnizori;
	}
	
	@Test
	public void test2_GetFurnizori() {
		logger.info("DEBUG: Junit TESTING: GetFurnizori ...");
		addFurnizori(5);
		Collection<Furnizor> furnizori = service.getFurnizori();
		
		assert furnizori.size() == 5;
	}
	
	@Test
	public void test3_GetFurnizorByID() {
		logger.info("DEBUG: Junit TESTING: GetFurnizorByID ...");
		
		addFurnizori(5);
		
		Collection<Furnizor> furnizori = service.getFurnizori();
		Integer idFurnizor = furnizori.stream().findFirst().get().getIdFurnizor();
		
		Furnizor furnizor = service.getFurnizorByFurnizorID(idFurnizor);
		
		assert furnizor.getIdFurnizor() == idFurnizor;
	}
	
	@Test
	public void test4_GetFurnizoriByFurnizorAdresa() {
		logger.info("DEBUG: Junit TESTING: GetFurnizoriByFurnizorAdresa ...");
		
		addFurnizori(5);
		
		Collection<Furnizor> furnizori = service.getFurnizoriByFurnizorAdresa("Adresa_2");
		
		assert furnizori.size() == 1 && furnizori.stream().findFirst().get().getAdresa() == "Adresa_2";
	}
	
	@Test
	public void test5_RemoveFurnizor() {
		logger.info("DEBUG: Junit TESTING: RemoveFurnizor ...");
		
		Collection<Furnizor> furnizori = service.getFurnizori();
		for (Furnizor c: furnizori) {
			service.removeFurnizor(c);
		}
		Collection<Furnizor> furnizoriDupaStergere = service.getFurnizori();
		
		assert furnizoriDupaStergere.size() == 0;
	}
	
}
