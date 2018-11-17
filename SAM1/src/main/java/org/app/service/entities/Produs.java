package org.app.service.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class Produs implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idProdus;
	private String denumire;
	@OneToMany(mappedBy="produs")
	private List<Licenta> licente = new ArrayList<Licenta>();
	public int getIdProdus() {
		return idProdus;
	}
	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public List<Licenta> getLicente() {
		return licente;
	}
	public void setLicente(List<Licenta> licente) {
		this.licente = licente;
	}
	public Produs(String denumire) {
		super();
		this.denumire = denumire;
	}
	public Produs() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denumire == null) ? 0 : denumire.hashCode());
		result = prime * result + idProdus;
		result = prime * result + ((licente == null) ? 0 : licente.hashCode());
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
		Produs other = (Produs) obj;
		if (denumire == null) {
			if (other.denumire != null)
				return false;
		} else if (!denumire.equals(other.denumire))
			return false;
		if (idProdus != other.idProdus)
			return false;
		if (licente == null) {
			if (other.licente != null)
				return false;
		} else if (!licente.equals(other.licente))
			return false;
		return true;
	}
	
	

}
