package org.app.service.ejb.test;


import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.SuportService;
import org.app.service.ejb.SuportServiceEJB;
import org.app.service.ejb.TipEchipaService;
import org.app.service.ejb.TipEchipaServiceEJB;
import org.app.service.entities.Suport;
import org.app.service.entities.TipEchipa;
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
public class TestSuportServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestSuportServiceEJBArq.class.getName());
	@EJB
	private static SuportService service;
	
	@EJB
	private static TipEchipaService tipEchipaService;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Suport.class.getPackage())
				.addPackage(TipEchipa.class.getPackage())
				
				.addClass(SuportService.class)
				.addClass(TipEchipaService.class)
				
				.addClass(SuportServiceEJB.class)
				.addClass(TipEchipaServiceEJB.class)
				
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	private void addSuports(Integer count) {
		String descriere = "Descriere_1";
		String departament = "Departament_1";
		TipEchipa tipEchipa = new TipEchipa(descriere, departament);
		tipEchipaService.addTipEchipa(tipEchipa);
	
		tipEchipa = tipEchipaService.getTipEchipe().stream().findFirst().get();
		
		for (int i = 1; i <= count; i++) {
			Suport suport = new Suport(tipEchipa, "Descriere_" + i);
			
			service.addSuport(suport);
		}
	}
	
	@Test
	public void test1_AddSuports() {
		logger.info("DEBUG: Junit TESTING: AddSuports ...");
		Integer count = 5;
		addSuports(count);
		Collection<Suport> suports = service.getSuports();
		
		assert suports.size() == count;
	}
	
	@Test
	public void test2_GetSuports() {
		logger.info("DEBUG: Junit TESTING: GetSuports ...");
		addSuports(5);
		Collection<Suport> suports = service.getSuports();
		
		assert suports.size() == 5;
	}
	
	@Test
	public void test3_GetSuportByID() {
		logger.info("DEBUG: Junit TESTING: GetSuportByID ...");
		
		addSuports(5);
		
		Collection<Suport> suports = service.getSuports();
		Integer idSuport = suports.stream().findFirst().get().getIdSuport();
		
		Suport suport = service.getSuportByID(idSuport);
		
		assert suport.getIdSuport() == idSuport;
	}
	
	@Test
	public void test4_GetSupportsByTipEchipaDepartament() {
		logger.info("DEBUG: Junit TESTING: GetSupportsByTipEchipaDepartament ...");
		
		addSuports(5);
		
		Collection<Suport> suports = service.getSupportsByTipEchipaDepartament("Departament_1");
		
		assert suports.size() == 5;
	}
	
	@Test
	public void test5_RemoveSuport() {
		logger.info("DEBUG: Junit TESTING: RemoveSuport ...");
		
		Collection<Suport> suports = service.getSuports();
		for (Suport suport: suports) {
			service.removeSuport(suport);
		}
		Collection<Suport> suportsDupaStergere = service.getSuports();
		
		assert suportsDupaStergere.size() == 0;
	}
	
}
