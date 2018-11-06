package org.app.service.entities;
import javax.persistence.*;
@Entity
public class Licenta {
	@Id
	@GeneratedValue
	private int idLicenta;
	private String descriere;
	private String tipLicenta;
	@ManyToOne
	private Produs produs;
	public int getIdLicenta() {
		return idLicenta;
	}
	public void setIdLicenta(int idLicenta) {
		this.idLicenta = idLicenta;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public String getTipLicenta() {
		return tipLicenta;
	}
	public void setTipLicenta(String tipLicenta) {
		this.tipLicenta = tipLicenta;
	}
	public Produs getProdus() {
		return produs;
	}
	public void setProdus(Produs produs) {
		this.produs = produs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriere == null) ? 0 : descriere.hashCode());
		result = prime * result + idLicenta;
		result = prime * result + ((produs == null) ? 0 : produs.hashCode());
		result = prime * result + ((tipLicenta == null) ? 0 : tipLicenta.hashCode());
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
		Licenta other = (Licenta) obj;
		if (descriere == null) {
			if (other.descriere != null)
				return false;
		} else if (!descriere.equals(other.descriere))
			return false;
		if (idLicenta != other.idLicenta)
			return false;
		if (produs == null) {
			if (other.produs != null)
				return false;
		} else if (!produs.equals(other.produs))
			return false;
		if (tipLicenta == null) {
			if (other.tipLicenta != null)
				return false;
		} else if (!tipLicenta.equals(other.tipLicenta))
			return false;
		return true;
	}
	public Licenta(int idLicenta, String descriere, String tipLicenta, Produs produs) {
		super();
		this.idLicenta = idLicenta;
		this.descriere = descriere;
		this.tipLicenta = tipLicenta;
		this.produs = produs;
	}
	public Licenta() {
		super();
	}

}
