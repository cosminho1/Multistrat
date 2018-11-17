package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.service.entities.Contract;

@Remote
public interface ContractService {
	// CREATE or UPDATE
	Contract addContract(Contract contractToAdd);
	
	// DELETE
	String removeContract(Contract contractToDelete);
	
	// READ
	Contract getContractByContractID(Integer contractID);
	Collection<Contract> getContracts();
	
	// Custom READ: custom query
	Collection<Contract> getContractsByProdusId(Integer produsID);
	
	// Others
	String sayRest();
}
