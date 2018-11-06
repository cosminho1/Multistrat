package org.app.service.entities;
import javax.persistence.*;
@Entity
public class TipEchipa {
	@Id
	@GeneratedValue
	private int idTipEchipa;
	private String descriere;
	private String departament;
	public TipEchipa() {
		super();
	}
	public TipEchipa(int idTipEchipa, String descriere, String departament) {
		super();
		this.idTipEchipa = idTipEchipa;
		this.descriere = descriere;
		this.departament = departament;
	}
	@Override
	public String toString() {
		return "TipEchipa [idTipEchipa=" + idTipEchipa + ", descriere=" + descriere + ", departament=" + departament
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departament == null) ? 0 : departament.hashCode());
		result = prime * result + ((descriere == null) ? 0 : descriere.hashCode());
		result = prime * result + idTipEchipa;
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
		TipEchipa other = (TipEchipa) obj;
		if (departament == null) {
			if (other.departament != null)
				return false;
		} else if (!departament.equals(other.departament))
			return false;
		if (descriere == null) {
			if (other.descriere != null)
				return false;
		} else if (!descriere.equals(other.descriere))
			return false;
		if (idTipEchipa != other.idTipEchipa)
			return false;
		return true;
	}
	public int getIdTipEchipa() {
		return idTipEchipa;
	}
	public void setIdTipEchipa(int idTipEchipa) {
		this.idTipEchipa = idTipEchipa;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public String getDepartament() {
		return departament;
	}
	public void setDepartament(String departament) {
		this.departament = departament;
	}

}
