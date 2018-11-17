package org.app.service.ejb.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.app.service.ejb.AngajatService;
import org.app.service.ejb.AngajatServiceEJB;
import org.app.service.ejb.ClientService;
import org.app.service.ejb.ClientServiceEJB;
import org.app.service.ejb.ContractService;
import org.app.service.ejb.ContractServiceEJB;
import org.app.service.ejb.FurnizorService;
import org.app.service.ejb.FurnizorServiceEJB;
import org.app.service.ejb.GarantieService;
import org.app.service.ejb.GarantieServiceEJB;
import org.app.service.ejb.ProdusService;
import org.app.service.ejb.ProdusServiceEJB;
import org.app.service.ejb.SuportService;
import org.app.service.ejb.SuportServiceEJB;
import org.app.service.ejb.TipEchipaService;
import org.app.service.ejb.TipEchipaServiceEJB;
import org.app.service.entities.Angajat;
import org.app.service.entities.Client;
import org.app.service.entities.Contract;
import org.app.service.entities.Furnizor;
import org.app.service.entities.Garantie;
import org.app.service.entities.Licenta;
import org.app.service.entities.Produs;
import org.app.service.entities.Suport;
import org.app.service.entities.TipEchipa;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestContractServiceEJBArq {
	private static Logger logger = 
			Logger.getLogger(TestContractServiceEJBArq.class.getName());
	@EJB
	private static ContractService service;
	@EJB
	private static ClientService clientService;
	@EJB
	private static ProdusService produsService;
	@EJB
	private static GarantieService garantieService;
	@EJB
	private static AngajatService angajatService;
	@EJB
	private static FurnizorService furnizorService;
	@EJB
	private static SuportService suportService;
	@EJB
	private static TipEchipaService tipEchipaService;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "SCRUM-S3-test.war")
				
				.addPackage(Contract.class.getPackage())
				.addPackage(Client.class.getPackage())
				.addPackage(Produs.class.getPackage())
				.addPackage(Garantie.class.getPackage())
				.addPackage(Angajat.class.getPackage())
				.addPackage(Furnizor.class.getPackage())
				.addPackage(Suport.class.getPackage())
				.addPackage(TipEchipa.class.getPackage())
				
				.addClass(ContractService.class)
				.addClass(ClientService.class)
				.addClass(ProdusService.class)
				.addClass(GarantieService.class)
				.addClass(AngajatService.class)
				.addClass(FurnizorService.class)
				.addClass(SuportService.class)
				.addClass(TipEchipaService.class)
				
				.addClass(ContractServiceEJB.class)
				.addClass(ClientServiceEJB.class)
				.addClass(ProdusServiceEJB.class)
				.addClass(GarantieServiceEJB.class)
				.addClass(AngajatServiceEJB.class)
				.addClass(FurnizorServiceEJB.class)
				.addClass(SuportServiceEJB.class)
				.addClass(TipEchipaServiceEJB.class)
				
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	private void addClients(Integer numberOfClients) {
		for (int i = 1; i <= numberOfClients; i++) {
			String denumire = "Denumire_" + i;
			String telefon = "Telefon_" + i;
			String adresa = "Adresa_" + i;
			String mail = "Mail_" + i;
			String cui = "Cui_" + i;
			
			Client clientToAdd = new Client(denumire, telefon, adresa, mail, cui);
			
			clientService.addClient(clientToAdd);
		}
	}
	
	private void addProducts(Integer numberOfProducts) {
		for (int i = 1; i<= numberOfProducts; i++) {
			String denumire = "Denumire_" + i;
			
			Produs produsToAdd = new Produs(denumire);
			
			produsService.addProdus(produsToAdd);
		}
	/*	for (Produs produs : produse) {
			
			List<Licenta> licente = produs.getLicente();
			for(int j = 1; j <= 3; j++) {
				String descriere = "Descriere_" + j;
				String tipLicenta = "TipLicenta_" + j;
				Licenta licenta = new Licenta(descriere, tipLicenta, produs);
				licente.add(licenta);
			}
			produs.setLicente(licente);
		} */
	}
	
	private void addGarantii(Integer numberOfGarantii) {
		for (int i = 1; i <= numberOfGarantii; i++) {
			String descriere = "Descriere_" + i;
			String durata = "Durata_" + i;
			Garantie garantie = new Garantie(descriere, durata);
			garantieService.addGarantie(garantie);
		}
	}
	
	private void addAngajati(Integer numberOfAngajati) {
		for (int i = 1; i <= numberOfAngajati; i++) {
			String nume = "Nume_" + i;
			String adresa = "Adresa_" + i;
			String telefon = "Telefon_" + i;
			String mail = "Mail_" + i;
			Angajat angajat = new Angajat(nume, adresa, telefon, mail);
			angajatService.addAngajat(angajat);
		}
	}
	
	private void addFurnizori(Integer numberOfFurnizori) {
		for (int i = 1; i <= numberOfFurnizori; i++) {
			String denumire = "Denumire_" + i;
			String telefon = "Telefon_" + i;
			String adresa = "Adresa_" + i;
			String mail = "Mail_" + i;
			String cui = "Cui_" + i;
			Furnizor furnizor = new Furnizor(denumire, telefon, adresa, mail, cui);
			furnizorService.addFurnizor(furnizor);
		}
	}
	
	private void addSuports(Integer numberOfSupports) {
		TipEchipa tipEchipa = new TipEchipa("Descriere_1", "Departament_1");
		tipEchipaService.addTipEchipa(tipEchipa);
		tipEchipa = tipEchipaService.getTipEchipeByDepartament("Departament_1").stream().findFirst().get();
		for (int i = 1; i <= numberOfSupports; i++) {
			String descriere = "Descriere_" + i;
			Suport suport = new Suport(tipEchipa, descriere);
			suportService.addSuport(suport);
		}
	}
	
	private void addContracts(Integer numberOfContracts) {
		addClients(numberOfContracts);
		Collection<Client> clients = clientService.getClients();
		Iterator<Client> clientIterator = clients.iterator();
		
		addProducts(numberOfContracts);
		Collection<Produs> produse = produsService.getProduse();
		Iterator<Produs> produsIterator = produse.iterator();
		
		addGarantii(numberOfContracts);
		Collection<Garantie> garantii = garantieService.getGarantii();
		Iterator<Garantie> garantieIterator = garantii.iterator();
		
		addAngajati(numberOfContracts);
		Collection<Angajat> angajati = angajatService.getAngajati();
		Iterator<Angajat> angajatIterator = angajati.iterator();
		
		addFurnizori(numberOfContracts);
		Collection<Furnizor> furnizori = furnizorService.getFurnizori();
		Iterator<Furnizor> furnizorIterator = furnizori.iterator();
		
		addSuports(numberOfContracts);
		Collection<Suport> suports = suportService.getSuports();
		Iterator<Suport> suportIterator = suports.iterator();
		
		for (Integer index = 1; index <= numberOfContracts; index++) {
			
			Client client = clientIterator.next();
			String descriere = "Descriere_" + index;
			Produs produs = produsIterator.next();
			Garantie garantie = garantieIterator.next();
			Angajat angajatResponsabil = angajatIterator.next();
			Furnizor furnizor = furnizorIterator.next();
			Suport suport = suportIterator.next();
			
			Contract contract = new Contract(client, descriere, produs, garantie, angajatResponsabil, furnizor, suport);
			
			service.addContract(contract);
		}
	}
	
	@Test
	public void test1_AddContract() {
		logger.info("DEBUG: Junit TESTING: testAddContract ...");
		
		Integer contracteToAdd = 5;
		addContracts(contracteToAdd);
		Collection<Contract> contracte = service.getContracts();
		
		assert contracte.size() == contracteToAdd;
	}
	
	@Test
	public void test2_GetContracts() {
		logger.info("DEBUG: Junit TESTING: testGetContracts ...");
		
		Collection<Contract> contracte = service.getContracts();
		
		assert contracte.size() == 5;
	}
	
	@Test
	public void test3_GetContractByContractID() {
		logger.info("DEBUG: Junit TESTING: GetContractByContractID ...");
		
		addContracts(5);
		
		Collection<Contract> contracte = service.getContracts();
		Integer idContract = contracte.iterator().next().getIdContract();
		
		
		Contract contract = service.getContractByContractID(idContract);
		assert idContract.equals(contract.getIdContract());
	}
	
	@Test
	public void test4_GetContractByProdusID() {
		logger.info("DEBUG: Junit TESTING: GetContractByProdusID ...");
		
		addContracts(5);
		
		Produs produs = produsService.getProduse().iterator().next();
		
		Collection<Contract> contracts = service.getContractsByProdusId(produs.getIdProdus());
		
		assert contracts.stream().findFirst().get().getProdus().getIdProdus() == produs.getIdProdus();
	}
	
	@Test
	public void test5_RemoveContract() {
		logger.info("DEBUG: Junit TESTING: testRemoveContract ...");
		
		Collection<Contract> contracte = service.getContracts();
		for (Contract c: contracte) {
			service.removeContract(c);
		}
		Collection<Contract> contractsAfterRemove = service.getContracts();
		
		assert contractsAfterRemove.size() == 0;
	}
}
