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
public class TestLicentaServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestLicentaServiceEJBArq.class.getName());
	
	@EJB
	private static LicentaService service;
	
	@EJB
	private static ProdusService produsService;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Licenta.class.getPackage())
				.addPackage(Produs.class.getPackage())
				
				.addClass(LicentaService.class)
				.addClass(ProdusService.class)
				
				
				.addClass(LicentaServiceEJB.class)
				.addClass(ProdusServiceEJB.class)
				
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	private void addLicente(Integer numarLicente) {
		Produs produs = new Produs("Produs_1");
		produsService.addProdus(produs);
		produs = produsService.getProduse().stream().findFirst().get();
		
		for (int i = 1; i <= numarLicente; i++) {
			String descriere = "Descriere_" + i;
			String tipLicenta = "TipLicenta_" + i;
			Licenta licenta = new Licenta(descriere, tipLicenta, produs);
			service.addLicenta(licenta);
		}
	}
	
	@Test
	public void test1_AddLicente() {
		logger.info("DEBUG: Junit TESTING: AddLicente ...");
		Integer addLicente = 5;
		addLicente(addLicente);
		Collection<Licenta> licente = service.getLicente();
		
		assert licente.size() == addLicente;
	}
	
	@Test
	public void test2_GetLicente() {
		logger.info("DEBUG: Junit TESTING: GetLicente ...");
		addLicente(5);
		Collection<Licenta> licente = service.getLicente();
		
		assert licente.size() == 5;
	}
	
	@Test
	public void test3_GetLicentaByID() {
		logger.info("DEBUG: Junit TESTING: GetLicentaByID ...");
		
		addLicente(5);
		
		Collection<Licenta> licente = service.getLicente();
		Integer idLicenta = licente.stream().findFirst().get().getIdLicenta();
		
		Licenta licenta = service.getLicentaByLicentaID(idLicenta);
		
		assert licenta.getIdLicenta() == idLicenta;
	}
	
	@Test
	public void test4_GetLicenteByTipLicenta() {
		logger.info("DEBUG: Junit TESTING: GetLicenteByTipLicenta ...");
		
		addLicente(5);
		
		Collection<Licenta> licente = service.getLicenteByTipLicenta("TipLicenta_1");
		
		assert licente.size() == 1 && licente.stream().findFirst().get().getTipLicenta() == "TipLicenta_1";
	}
	
	@Test
	public void test5_RemoveLicenta() {
		logger.info("DEBUG: Junit TESTING: RemoveLicenta ...");
		
		Collection<Licenta> licente = service.getLicente();
		for (Licenta c: licente) {
			service.removeLicenta(c);
		}
		Collection<Licenta> licenteDupaStergere = service.getLicente();
		
		assert licenteDupaStergere.size() == 0;
	}
	
}
