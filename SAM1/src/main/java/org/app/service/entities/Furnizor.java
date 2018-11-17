package org.app.service.entities;
import java.io.Serializable;

import javax.persistence.*;
@Entity
public class Furnizor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idFurnizor;
	private String denumire;
	private String telefon;
	private String adresa;
	private String mail;
	private String cui;
	public int getIdFurnizor() {
		return idFurnizor;
	}
	public void setIdFurnizor(int idFurnizor) {
		this.idFurnizor = idFurnizor;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresa == null) ? 0 : adresa.hashCode());
		result = prime * result + ((cui == null) ? 0 : cui.hashCode());
		result = prime * result + ((denumire == null) ? 0 : denumire.hashCode());
		result = prime * result + idFurnizor;
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
		Furnizor other = (Furnizor) obj;
		if (adresa == null) {
			if (other.adresa != null)
				return false;
		} else if (!adresa.equals(other.adresa))
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
		if (idFurnizor != other.idFurnizor)
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
	public Furnizor(String denumire, String telefon, String adresa, String mail, String cui) {
		super();
		this.denumire = denumire;
		this.telefon = telefon;
		this.adresa = adresa;
		this.mail = mail;
		this.cui = cui;
	}
	public Furnizor() {
		super();
	}
	
}
