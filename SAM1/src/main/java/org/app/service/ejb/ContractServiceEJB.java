package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.app.service.entities.Contract;

@Stateless @LocalBean
public class ContractServiceEJB implements ContractService {
	@PersistenceContext(unitName="MSD")
	private EntityManager em;
	
	@Override
	public Contract addContract(Contract contractToAdd) {
		em.persist(contractToAdd);
		em.flush();
		em.refresh(contractToAdd);
		return contractToAdd;
	}

	@Override
	public String removeContract(Contract contractToDelete) {
		contractToDelete = em.merge(contractToDelete);
		em.remove(contractToDelete);
		em.flush();
		return "True";
	}

	@Override
	public Contract getContractByContractID(Integer contractID) {
		TypedQuery<Contract> query =  em.createQuery("SELECT c FROM Contract c WHERE c.idContract = :contractID ", Contract.class);
		query.setParameter("contractID", contractID);
		List<Contract> contracte = (List<Contract>) query.getResultList();
		return contracte.stream().findFirst().get();
	}

	@Override
	public Collection<Contract> getContracts() {
		List<Contract> contracte = em.createQuery("SELECT c FROM Contract c", Contract.class).getResultList();
		return contracte;
	}

	@Override
	public Collection<Contract> getContractsByProdusId(Integer produsID) {
		TypedQuery<Contract> query =  em.createQuery("SELECT c FROM Contract c WHERE c.produs.idProdus = :produsID ", Contract.class);
		query.setParameter("produsID", produsID);
		List<Contract> contracte = (List<Contract>) query.getResultList();
		return contracte;
	}

	@Override
	public String sayRest() {
		return "Contract Service is On...";
	}

}
