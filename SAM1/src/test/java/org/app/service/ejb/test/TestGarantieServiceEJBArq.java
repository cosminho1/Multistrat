package org.app.service.ejb.test;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.GarantieService;
import org.app.service.ejb.GarantieServiceEJB;
import org.app.service.entities.Garantie;
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
public class TestGarantieServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestGarantieServiceEJBArq.class.getName());
	@EJB
	private static GarantieService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Garantie.class.getPackage())
				.addClass(GarantieService.class)
				.addClass(GarantieServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	private void addGarantii(Integer numarGarantii) {
		for (int i = 1; i <= numarGarantii; i++) {
			String descriere = "Descriere_" + i;
			String durata = "";
			if (i % 2 == 0) {
				durata = "1";
			} else {
				durata = "2";
			}
			Garantie garantie = new Garantie(descriere, durata);
			service.addGarantie(garantie);
		}
	}
	
	@Test
	public void test1_AddGarantie() {
		logger.info("DEBUG: Junit TESTING: AddGarantie ...");
		Integer numarGarantii = 5;
		addGarantii(numarGarantii);
		Collection<Garantie> garantii = service.getGarantii();
		
		assert garantii.size() == numarGarantii;
	}
	
	@Test
	public void test2_GetGarantii() {
		logger.info("DEBUG: Junit TESTING: GetGarantii ...");
		addGarantii(5);
		Collection<Garantie> garantii = service.getGarantii();
		
		assert garantii.size() == 5;
	}
	
	@Test
	public void test3_GetGarantieByID() {
		logger.info("DEBUG: Junit TESTING: GetGarantieByID ...");
		
		addGarantii(5);
		
		Collection<Garantie> garantii = service.getGarantii();
		Integer idGarantie = garantii.stream().findFirst().get().getIdGarantie();
		
		Garantie garantie = service.getGarantieByGarantieID(idGarantie);
		
		assert garantie.getIdGarantie() == idGarantie;
	}
	
	@Test
	public void test4_GetGarantiiByDurata() {
		logger.info("DEBUG: Junit TESTING: GetGarantiiByDurata ...");
		
		addGarantii(5);
		
		Collection<Garantie> garantii = service.getGarantiiByDurata("1");
		
		assert garantii.size() == 2;
	}
	
	@Test
	public void test5_RemoveGarantie() {
		logger.info("DEBUG: Junit TESTING: RemoveGarantie ...");
		
		Collection<Garantie> garantii = service.getGarantii();
		for (Garantie c: garantii) {
			service.removeGarantie(c);
		}
		Collection<Garantie> garantiiDupaStergere = service.getGarantii();
		
		assert garantiiDupaStergere.size() == 0;
	}
}
