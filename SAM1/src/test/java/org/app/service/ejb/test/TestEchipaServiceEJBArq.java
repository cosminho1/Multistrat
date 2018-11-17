package org.app.service.ejb.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.app.service.ejb.EchipaService;
import org.app.service.ejb.EchipaServiceEJB;
import org.app.service.ejb.TipEchipaService;
import org.app.service.ejb.TipEchipaServiceEJB;
import org.app.service.entities.Echipa;
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
public class TestEchipaServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestEchipaServiceEJBArq.class.getName());
	@EJB
	private static EchipaService service;
	@EJB
	private static TipEchipaService tipEchipaService;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(Echipa.class.getPackage())
				.addPackage(TipEchipa.class.getPackage())
				
				.addClass(EchipaService.class)
				.addClass(TipEchipaService.class)
				
				.addClass(EchipaServiceEJB.class)
				.addClass(TipEchipaServiceEJB.class)
				
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	private void addEchipe(Integer numberOfEchipe) {
		TipEchipa tipEchipa = new TipEchipa("Descriere", "Departament");
		tipEchipaService.addTipEchipa(tipEchipa);
		tipEchipa = tipEchipaService.getTipEchipe().stream().findFirst().get();
		
		for (int i = 1; i <= numberOfEchipe; i++) {
			String denumire = "Denumire_" + i;
			String specializare = "Specializare_" + i;
			
			Echipa echipa = new Echipa(denumire, specializare, tipEchipa);
			service.addEchipa(echipa);
		}
	}
	
	@Test
	public void test1_AddEchipe() {
		logger.info("DEBUG: Junit TESTING: testAddEchipa ...");
		System.out.println("debug..........");
		Integer echipeToAdd = 5;
		addEchipe(echipeToAdd);
		Collection<Echipa> echipe = service.getEchipe();
		
		assertTrue("Fail to add echipe", echipe.size() == echipeToAdd);
	}
	
	@Test
	public void test2_GetEchipe() {
		addEchipe(5);
		logger.info("DEBUG: Junit TESTING: testGetEchipe ...");
		
		List<Echipa> echipe = (List<Echipa>) service.getEchipe();
	//	throw new RuntimeException("" + echipe.size());
		assert echipe.size() == 5;
		//assertTrue("Fail to read echipe!", echipe.size() == 0);
	}
	
	@Test
	public void test3_GetEchipaByEchipaID() {
		logger.info("DEBUG: Junit TESTING: GetEchipaByEchipaID ...");
		
		addEchipe(5);
		Collection<Echipa> echipe = service.getEchipe();
		
		Integer idEchipa = echipe.stream().findFirst().get().getIdEchipa();
		
		Echipa echipa = service.getEchipaByEchipaID(idEchipa);
		
		assertTrue("Get Echipa By Id Failed!", idEchipa.equals(echipa.getIdEchipa()));
	}
	
	@Test
	public void test4_GetEchipeByTipEchipaID() {
		logger.info("DEBUG: Junit TESTING: GetEchipeByTipEchipaID ...");
		Integer nrEchipe = 5;
		addEchipe(nrEchipe);
		Collection<Echipa> echipe = service.getEchipe();
		
		Integer idTipEchipa = echipe.stream().findFirst().get().getTipEchipa().getIdTipEchipa();
		
		Collection<Echipa> echipe2 = service.getEchipeByTipEchipaID(idTipEchipa);
		
		assertTrue("Get Echipe By Id Tip Echipa Failed!", echipe2.stream().findFirst().get().getTipEchipa().getIdTipEchipa() == idTipEchipa);
	}
	
	@Test
	public void test5_RemoveEchipa() {
		logger.info("DEBUG: Junit TESTING: testRemoveEchipa ...");
		
		Collection<Echipa> echipe = service.getEchipe();
		for (Echipa c: echipe) {
			service.removeEchipa(c);
		}
		Collection<Echipa> echipeAfterRemove = service.getEchipe();
		
		assertTrue("Fail to read echipe!", echipeAfterRemove.size() == 0);
	}
}
