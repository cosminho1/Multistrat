package org.app.service.entities;
import java.io.Serializable;

import javax.persistence.*;
@Entity
public class Contract implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idContract;
	@ManyToOne
	private Client client;
	private String descriere;
	@ManyToOne
	private Produs produs;
	@ManyToOne
	private Garantie garantie;
	@ManyToOne
	private Angajat angajatResponsabil;
	@ManyToOne
	private Furnizor furnizor;
	@ManyToOne
	private Suport suport;
	public int getIdContract() {
		return idContract;
	}
	public void setIdContract(int idContract) {
		this.idContract = idContract;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public Produs getProdus() {
		return produs;
	}
	public void setProdus(Produs produs) {
		this.produs = produs;
	}
	public Garantie getGarantie() {
		return garantie;
	}
	public void setGarantie(Garantie garantie) {
		this.garantie = garantie;
	}
	public Angajat getAngajatResponsabil() {
		return angajatResponsabil;
	}
	public void setAngajatResponsabil(Angajat angajatResponsabil) {
		this.angajatResponsabil = angajatResponsabil;
	}
	public Furnizor getFurnizor() {
		return furnizor;
	}
	public void setFurnizor(Furnizor furnizor) {
		this.furnizor = furnizor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((angajatResponsabil == null) ? 0 : angajatResponsabil.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((descriere == null) ? 0 : descriere.hashCode());
		result = prime * result + ((furnizor == null) ? 0 : furnizor.hashCode());
		result = prime * result + ((garantie == null) ? 0 : garantie.hashCode());
		result = prime * result + idContract;
		result = prime * result + ((produs == null) ? 0 : produs.hashCode());
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
		Contract other = (Contract) obj;
		if (angajatResponsabil == null) {
			if (other.angajatResponsabil != null)
				return false;
		} else if (!angajatResponsabil.equals(other.angajatResponsabil))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (descriere == null) {
			if (other.descriere != null)
				return false;
		} else if (!descriere.equals(other.descriere))
			return false;
		if (furnizor == null) {
			if (other.furnizor != null)
				return false;
		} else if (!furnizor.equals(other.furnizor))
			return false;
		if (garantie == null) {
			if (other.garantie != null)
				return false;
		} else if (!garantie.equals(other.garantie))
			return false;
		if (idContract != other.idContract)
			return false;
		if (produs == null) {
			if (other.produs != null)
				return false;
		} else if (!produs.equals(other.produs))
			return false;
		return true;
	}
	public Contract(Client client, String descriere, Produs produs, Garantie garantie,
			Angajat angajatResponsabil, Furnizor furnizor, Suport suport) {
		super();
		this.client = client;
		this.descriere = descriere;
		this.produs = produs;
		this.garantie = garantie;
		this.angajatResponsabil = angajatResponsabil;
		this.furnizor = furnizor;
		this.suport = suport;
	}
	public Contract() {
		super();
	}
	
	
}
