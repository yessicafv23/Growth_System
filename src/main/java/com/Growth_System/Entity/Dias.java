package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Dias implements Serializable{
	private static final long serialVersionUID = -6833167247955613395L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDDias;
	
	@Column
	private String dias;

	public Dias() {
		super();
	}

	public Dias(Long IDDias) {
		super();
		this.IDDias = IDDias;
	}

	public Long getId_dias() {
		return IDDias;
	}

	public void setId_dias(Long IDDias) {
		this.IDDias = IDDias;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	@Override
	public String toString() {
		return "Dias [IDDias=" + IDDias + ", dias=" + dias + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dias == null) ? 0 : dias.hashCode());
		result = prime * result + ((IDDias == null) ? 0 : IDDias.hashCode());
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
		Dias other = (Dias) obj;
		if (dias == null) {
			if (other.dias != null)
				return false;
		} else if (!dias.equals(other.dias))
			return false;
		if (IDDias == null) {
			if (other.IDDias != null)
				return false;
		} else if (!IDDias.equals(other.IDDias))
			return false;
		return true;
	}
	

}

