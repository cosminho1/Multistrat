package org.app.service.ejb.test;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.TipEchipaService;
import org.app.service.ejb.TipEchipaServiceEJB;
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
public class TestTipEchipaServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestTipEchipaServiceEJBArq.class.getName());

	@EJB
	private static TipEchipaService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(TipEchipa.class.getPackage())
				.addClass(TipEchipaService.class)
				.addClass(TipEchipaServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	
	private void addTipEchipe(Integer count) {
		for (int i = 1; i <= count; i++) {
			String descriere = "Descriere_" + i;
			String departament = "Departament_1" + i;
			TipEchipa tipEchipa = new TipEchipa(descriere, departament);
			service.addTipEchipa(tipEchipa);
		}
	}
	
	@Test
	public void test1_AddTipEchipa() {
		logger.info("DEBUG: Junit TESTING: AddTipEchipa ...");
		Integer count = 5;
		addTipEchipe(count);
		Collection<TipEchipa> tipEchipe = service.getTipEchipe();
		
		assert tipEchipe.size() == count;
	}
	
	@Test
	public void test2_GetTipEchipe() {
		logger.info("DEBUG: Junit TESTING: GetTipEchipe ...");
		addTipEchipe(5);
		Collection<TipEchipa> tipEchipe = service.getTipEchipe();
		
		assert tipEchipe.size() == 5;
	}
	
	@Test
	public void test3_GetTipEchipaByID() {
		logger.info("DEBUG: Junit TESTING: GetTipEchipaByID ...");
		
		addTipEchipe(5);
		
		Collection<TipEchipa> tipEchipe = service.getTipEchipe();
		Integer idTipEchipa = tipEchipe.stream().findFirst().get().getIdTipEchipa();
		
		TipEchipa tipEchipa = service.getTipEchipaByID(idTipEchipa);
		
		assert tipEchipa.getIdTipEchipa() == idTipEchipa;
	}
	
	@Test
	public void test4_GetTipEchipeByDepartament() {
		logger.info("DEBUG: Junit TESTING: GetTipEchipeByDepartament ...");
		
		addTipEchipe(5);
		
		Collection<TipEchipa> tipEchipe = service.getTipEchipeByDepartament("Departament_1");
		
		assert tipEchipe.size() == 1;
	}
	
	@Test
	public void test5_RemoveTipEchipa() {
		logger.info("DEBUG: Junit TESTING: RemoveTipEchipa ...");
		
		Collection<TipEchipa> tipEchipe = service.getTipEchipe();
		for (TipEchipa tipEchipa: tipEchipe) {
			service.removeTipEchipa(tipEchipa);
		}
		Collection<TipEchipa> tipEchipeDupaStergere = service.getTipEchipe();
		
		assert tipEchipeDupaStergere.size() == 0;
	}
}
