package org.app.service.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class Echipa {
	@Id
	@GeneratedValue
	private int idEchipa;
	private String denumire;
	private String specializare;
	@OneToOne
	private TipEchipa tipEchipa;
	@OneToMany(mappedBy="echipa")
	private List <Angajat> angajati = new ArrayList<Angajat>();
	public List<Angajat> getAngajati() {
		return angajati;
	}
	public void setAngajati(List<Angajat> angajati) {
		this.angajati = angajati;
	}
	
	public TipEchipa getTipEchipa() {
		return tipEchipa;
	}
	public void setTipEchipa(TipEchipa tipEchipa) {
		this.tipEchipa = tipEchipa;
	}
	public Echipa(int idEchipa, String denumire, String specializare, TipEchipa tipEchipa, List<Angajat> angajati) {
		super();
		this.idEchipa = idEchipa;
		this.denumire = denumire;
		this.specializare = specializare;
		this.tipEchipa = tipEchipa;
		this.angajati = angajati;
	}
	public int getIdEchipa() {
		return idEchipa;
	}
	public void setIdEchipa(int idEchipa) {
		this.idEchipa = idEchipa;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public String getSpecializare() {
		return specializare;
	}
	public void setSpecializare(String specializare) {
		this.specializare = specializare;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((angajati == null) ? 0 : angajati.hashCode());
		result = prime * result + ((denumire == null) ? 0 : denumire.hashCode());
		result = prime * result + idEchipa;
		result = prime * result + ((specializare == null) ? 0 : specializare.hashCode());
		result = prime * result + ((tipEchipa == null) ? 0 : tipEchipa.hashCode());
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
		Echipa other = (Echipa) obj;
		if (angajati == null) {
			if (other.angajati != null)
				return false;
		} else if (!angajati.equals(other.angajati))
			return false;
		if (denumire == null) {
			if (other.denumire != null)
				return false;
		} else if (!denumire.equals(other.denumire))
			return false;
		if (idEchipa != other.idEchipa)
			return false;
		if (specializare == null) {
			if (other.specializare != null)
				return false;
		} else if (!specializare.equals(other.specializare))
			return false;
		if (tipEchipa == null) {
			if (other.tipEchipa != null)
				return false;
		} else if (!tipEchipa.equals(other.tipEchipa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Echipa [idEchipa=" + idEchipa + ", denumire=" + denumire + ", specializare=" + specializare
				+ ", tipEchipa=" + tipEchipa + ", angajati=" + angajati + "]";
	}
	public Echipa() {
		super();
	}

}
