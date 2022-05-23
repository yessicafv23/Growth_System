package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ciudad implements Serializable{
	private static final long serialVersionUID = -6833167247955613395L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDCiudad;
	
	@Column
	private String dpto;
	
	@Column
	private String ciudad;
	
	//Constructor
	
	public Ciudad() {
		super();
	}

	public Ciudad(Long iDCiudad) {
		super();
		IDCiudad = iDCiudad;
	}

	public Long getIDCiudad() {
		return IDCiudad;
	}

	public void setIDCiudad(Long iDCiudad) {
		this.IDCiudad = iDCiudad;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Ciudad [IDCiudad=" + IDCiudad + ", dpto=" + dpto + ", ciudad=" + ciudad + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((dpto == null) ? 0 : dpto.hashCode());
		result = prime * result + ((IDCiudad == null) ? 0 : IDCiudad.hashCode());
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
		Ciudad other = (Ciudad) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (dpto == null) {
			if (other.dpto != null)
				return false;
		} else if (!dpto.equals(other.dpto))
			return false;
		if (IDCiudad == null) {
			if (other.IDCiudad != null)
				return false;
		} else if (!IDCiudad.equals(other.IDCiudad))
			return false;
		return true;
	}
	
}
