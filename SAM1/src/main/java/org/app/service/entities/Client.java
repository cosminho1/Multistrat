package org.app.service.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class Client implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idClient;
	private String denumire;
	private String telefon;
	private String adresa;
	private String mail;
	private String cui;
	@OneToMany(mappedBy="client")
	private List <Contract> contracte = new ArrayList<Contract>();
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCui() {
		return cui;
	}
	public void setCui(String cui) {
		this.cui = cui;
	}
	public List<Contract> getContracte() {
		return contracte;
	}
	public void setContracte(List<Contract> contracte) {
		this.contracte = contracte;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresa == null) ? 0 : adresa.hashCode());
		result = prime * result + ((contracte == null) ? 0 : contracte.hashCode());
		result = prime * result + ((cui == null) ? 0 : cui.hashCode());
		result = prime * result + ((denumire == null) ? 0 : denumire.hashCode());
		result = prime * result + idClient;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
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
		Client other = (Client) obj;
		if (adresa == null) {
			if (other.adresa != null)
				return false;
		} else if (!adresa.equals(other.adresa))
			return false;
		if (contracte == null) {
			if (other.contracte != null)
				return false;
		} else if (!contracte.equals(other.contracte))
			return false;
		if (cui == null) {
			if (other.cui != null)
				return false;
		} else if (!cui.equals(other.cui))
			return false;
		if (denumire == null) {
			if (other.denumire != null)
				return false;
		} else if (!denumire.equals(other.denumire))
			return false;
		if (idClient != other.idClient)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		return true;
	}
	public Client(String denumire, String telefon, String adresa, String mail, String cui) {
		super();
		this.denumire = denumire;
		this.telefon = telefon;
		this.adresa = adresa;
		this.mail = mail;
		this.cui = cui;
	}
	public Client() {
		super();
	}
	
}
