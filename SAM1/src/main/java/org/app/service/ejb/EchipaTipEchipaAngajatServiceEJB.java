package org.app.service.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Angajat;
import org.app.service.entities.Echipa;
import org.app.service.entities.TipEchipa;

@Stateless @LocalBean
public class EchipaTipEchipaAngajatServiceEJB extends EntityRepositoryBase<Echipa> implements EchipaTipEchipaAngajatService {
	private static Logger logger = Logger.getLogger(EchipaTipEchipaAngajatServiceEJB.class.getName());
	
	@EJB
	private EchipaService echipaService;
	@EJB
	private TipEchipaService tipEchipaService;
	@EJB
	private AngajatService angajatService;
	
	private EntityRepositoryBase<Echipa> echipaRepository;

	@PostConstruct
	public void init() {
		echipaRepository = new EntityRepositoryBase<Echipa>(this.em, Echipa.class);
		logger.info("POSTCONSTRUCT-INIT echipaRepository:" + echipaRepository);
		logger.info("POSTCONSTRUCT-INIT echipaService:" + echipaService);
	}
	
	private List<TipEchipa> createTipEchipa(Integer count) {

		for(int i = 1; i <= count; i++) {
			TipEchipa tipEchipa = new TipEchipa("Descriere_" + i, "Departament_" + i);
			tipEchipaService.addTipEchipa(tipEchipa);
		}
	
		List<TipEchipa> tipEchipe = (List<TipEchipa>) tipEchipaService.getTipEchipe();
		
		return tipEchipe;
	}
	
	private List<Angajat> createAngajati(Integer count) {
		for (int i = 1; i <= count * 5; i++) {
			String nume = "Nume_" + i;
			String adresa = "Adresa_" + i;
			String telefon = "Telefon_" + i;
			String mail = "Mail_" + i;
			Angajat angajat = new Angajat(nume, adresa, telefon, mail);
			angajatService.addAngajat(angajat);
		}
		List<Angajat> angajati = (List<Angajat>) angajatService.getAngajati();
		return angajati;
	}
	
	@Override
	public List<Echipa> createEchipe(Integer numarEchipe) {
		List<Angajat> angajati = createAngajati(numarEchipe);
		List<TipEchipa> tipEchipe = createTipEchipa(numarEchipe);
		List<Echipa> echipe = new ArrayList<Echipa>();
		for (int i = 0; i < numarEchipe; i++) {
			String denumire = "Denumire_" + i;
			String specializare = "Specializare_" + i;
			TipEchipa tipEchipa = tipEchipe.get(i);
			List<Angajat> angajatiSelectati = new ArrayList<Angajat>();
			for (int j = i * 5; j < i * 5 + 5; j++) {
				angajatiSelectati.add(angajati.get(j));
			}
			
			Echipa echipa = new Echipa(denumire, specializare, tipEchipa);
			echipa.setAngajati(angajatiSelectati);
			echipe.add(echipa);
		}
		
		this.addAll(echipe);
		return echipe;
	}

	@Override
	public Echipa getEchipa(Integer idEchipa) {
		return echipaRepository.getById(idEchipa);
	}
	
	@Override
	public String getMessage() {
		return "Service is working";
	}
	
}
