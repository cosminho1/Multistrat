package org.app.service.entities;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;
@Entity
public class Angajat {
    @Id
	@GeneratedValue
	private int idAngajat;
	private String nume;
	private String adresa;
	private String telefon;
	private String mail;
	@ManyToOne
	private Echipa echipa;
	public int getIdAngajat() {
		return idAngajat;
	}
	public void setIdAngajat(int idAngajat) {
		this.idAngajat = idAngajat;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Angajat(int idAngajat, String nume, String adresa, String telefon, String mail, Echipa echipa) {
		super();
		this.idAngajat = idAngajat;
		this.nume = nume;
		this.adresa = adresa;
		this.telefon = telefon;
		this.mail = mail;
		this.echipa = echipa;
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
