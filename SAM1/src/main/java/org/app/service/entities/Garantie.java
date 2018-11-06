package org.app.service.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class Garantie {
	@Id
	@GeneratedValue
	private int idGarantie;
	private String descriere;
	private String durata;
	@OneToMany(mappedBy="garantie")
	private List<Contract> contract = new ArrayList<Contract>();
	public int getIdGarantie() {
		return idGarantie;
	}
	public void setIdGarantie(int idGarantie) {
		this.idGarantie = idGarantie;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public String getDurata() {
		return durata;
	}
	public void setDurata(String durata) {
		this.durata = durata;
	}
	public List<Contract> getContract() {
		return contract;
	}
	public void setContract(List<Contract> contract) {
		this.contract = contract;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contract == null) ? 0 : contract.hashCode());
		result = prime * result + ((descriere == null) ? 0 : descriere.hashCode());
		result = prime * result + ((durata == null) ? 0 : durata.hashCode());
		result = prime * result + idGarantie;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Garantie other = (Garantie) obj;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		if (descriere == null) {
			if (other.descriere != null)
				return false;
		} else if (!descriere.equals(other.descriere))
			return false;
		if (durata == null) {
			if (other.durata != null)
				return false;
		} else if (!durata.equals(other.durata))
			return false;
		if (idGarantie != other.idGarantie)
			return false;
		return true;
	}
	public Garantie(int idGarantie, String descriere, String durata, List<Contract> contract) {
		super();
		this.idGarantie = idGarantie;
		this.descriere = descriere;
		this.durata = durata;
		this.contract = contract;
	}
	public Garantie() {
		super();
	}
	
}
