package org.app.service.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
public class Suport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int idSuport;
	@OneToMany(mappedBy="suport")
	private List<Contract> contracte = new ArrayList<Contract>();
	@ManyToOne
	private TipEchipa tipEchipa;
	private String descriere;
	public int getIdSuport() {
		return idSuport;
	}
	public void setIdSuport(int idSuport) {
		this.idSuport = idSuport;
	}
	public List<Contract> getContract() {
		return contracte;
	}
	public void setContract(List<Contract> contracte) {
		this.contracte = contracte;
	}
	public TipEchipa getTipEchipa() {
		return tipEchipa;
	}
	public void setTipEchipa(TipEchipa tipEchipa) {
		this.tipEchipa = tipEchipa;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contracte == null) ? 0 : contracte.hashCode());
		result = prime * result + ((descriere == null) ? 0 : descriere.hashCode());
		result = prime * result + idSuport;
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
		Suport other = (Suport) obj;
		if (contracte == null) {
			if (other.contracte != null)
				return false;
		} else if (!contracte.equals(other.contracte))
			return false;
		if (descriere == null) {
			if (other.descriere != null)
				return false;
		} else if (!descriere.equals(other.descriere))
			return false;
		if (idSuport != other.idSuport)
			return false;
		if (tipEchipa == null) {
			if (other.tipEchipa != null)
				return false;
		} else if (!tipEchipa.equals(other.tipEchipa))
			return false;
		return true;
	}
	public Suport(TipEchipa tipEchipa, String descriere) {
		super();
		this.tipEchipa = tipEchipa;
		this.descriere = descriere;
	}
	public Suport() {
		super();
	}
	
}
