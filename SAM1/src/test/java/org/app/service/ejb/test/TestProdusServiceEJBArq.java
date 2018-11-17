package org.app.service.ejb.test;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.LicentaService;
import org.app.service.ejb.LicentaServiceEJB;
import org.app.service.ejb.ProdusService;
import org.app.service.ejb.ProdusServiceEJB;
import org.app.service.entities.Licenta;
import org.app.service.entities.Produs;
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
public class TestProdusServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestProdusServiceEJBArq.class.getName());
	
	@EJB
	private static ProdusService service;
	
	@EJB
	private static LicentaService licentaService;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Produs.class.getPackage())
				.addPackage(Licenta.class.getPackage())
				
				.addClass(ProdusService.class)
				.addClass(LicentaService.class)
				
				.addClass(ProdusServiceEJB.class)
				.addClass(LicentaServiceEJB.class)
				
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	private void addProduse(Integer numarProduse) {
		for (int i = 1; i <= numarProduse; i++) {
			Produs produs = new Produs("Produs_" + i);
			service.addProdus(produs);
		}
		
		Collection<Produs> produse = service.getProduse();
		
		for (Produs produs : produse) {
			String descriere = "Descriere_1";
			String tipLicenta = "TipLicenta_1";
			Licenta licenta = new Licenta(descriere, tipLicenta, produs);
			licentaService.addLicenta(licenta);
		}
	}
	
	@Test
	public void test1_AddProduse() {
		logger.info("DEBUG: Junit TESTING: AddProduse ...");
		Integer addProduse = 5;
		addProduse(addProduse);
		Collection<Produs> produse = service.getProduse();
		
		assert produse.size() == addProduse;
	}
	
	@Test
	public void test2_GetProduse() {
		logger.info("DEBUG: Junit TESTING: GetProduse ...");
		addProduse(5);
		Collection<Produs> produse = service.getProduse();
		
		assert produse.size() == 5;
	}
	
	@Test
	public void test3_GetProdusByID() {
		logger.info("DEBUG: Junit TESTING: GetProdusByID ...");
		
		addProduse(5);
		
		Collection<Produs> produse = service.getProduse();
		Integer idProdus = produse.stream().findFirst().get().getIdProdus();
		
		Produs produs = service.getProdusByID(idProdus);
		
		assert produs.getIdProdus() == idProdus;
	}
	
	@Test
	public void test4_GetProduseByLicentaTipLicenta() {
		logger.info("DEBUG: Junit TESTING: GetProduseByLicentaTipLicenta ...");
		
		addProduse(5);
		
		Collection<Produs> produse = service.getProduseByLicentaTipLicenta("TipLicenta_1");
		
		assert produse.size() == 5;
	}
	
	@Test
	public void test5_RemoveProdus() {
		logger.info("DEBUG: Junit TESTING: RemoveProdus ...");
		
		Collection<Licenta> licente = licentaService.getLicente();
		for (Licenta licenta : licente) {
			licentaService.removeLicenta(licenta);
		}
		
		Collection<Produs> produse = service.getProduse();
		for (Produs produs: produse) {
			service.removeProdus(produs);
		}
		Collection<Produs> produseDupaStergere = service.getProduse();
		
		assert produseDupaStergere.size() == 0;
	}
	
}
