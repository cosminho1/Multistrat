package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.AngajatServiceEJB;
import org.app.service.ejb.EchipaService;
import org.app.service.ejb.EchipaServiceEJB;
import org.app.service.ejb.EchipaTipEchipaAngajatService;
import org.app.service.ejb.EchipaTipEchipaAngajatServiceEJB;
import org.app.service.ejb.TipEchipaService;
import org.app.service.entities.Angajat;
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
public class EchipaTipEchipaAngajatServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestAngajatServiceEJBArq.class.getName());
	@EJB
	private static EchipaTipEchipaAngajatService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				.addPackage(EntityRepository.class.getPackage())
				.addPackage(Echipa.class.getPackage())
				.addPackage(TipEchipa.class.getPackage())
				.addPackage(Angajat.class.getPackage())
				.addPackage(TipEchipaService.class.getPackage())
				
				.addClass(EchipaService.class)
				.addClass(EchipaTipEchipaAngajatService.class)
				
				.addClass(EchipaServiceEJB.class)
				.addClass(EchipaTipEchipaAngajatServiceEJB.class)
				.addClass(TestAngajatServiceEJBArq.class)
				
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	/**
	 * 
	 */
	@Test
	public void test1_CreateEchipe() {
		List<Echipa> echipe = service.createEchipe(5);
		assertNotNull("Fail to create echipe!", echipe);
	}
	
	
	@Test
	public void test2_GetEchipa() {
		List<Echipa> echipe = service.createEchipe(5);
		Echipa echipa1 = echipe.stream().findFirst().get();
		Echipa echipa2 = service.getEchipa(echipa1.getIdEchipa());
		
		assert echipa1.getIdEchipa() == echipa2.getIdEchipa();
	}
	
}
