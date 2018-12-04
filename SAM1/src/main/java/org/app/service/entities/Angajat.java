package org.app.service.entities;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
@XmlRootElement(name="angajat")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Angajat implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idAngajat;
	private String nume;
	private String adresa;
	private String telefon;
	private String mail;
	@ManyToOne
	private Echipa echipa;
	@XmlElement
	public int getIdAngajat() {
		return idAngajat;
	}
	@XmlElement
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	@XmlElement
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	@XmlElement
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	@XmlElement
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Angajat(String nume, String adresa, String telefon, String mail) {
		super();
		this.nume = nume;
		this.adresa = adresa;
		this.telefon = telefon;
		this.mail = mail;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAngajat;
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
		Angajat other = (Angajat) obj;
		if (idAngajat != other.idAngajat)
			return false;
		return true;
	}
	@XmlElement
	public Echipa getEchipa() {
		return echipa;
	}
	public void setEchipa(Echipa echipa) {
		this.echipa = echipa;
	}
	public Angajat() {
		super();
	}
	
	

}
