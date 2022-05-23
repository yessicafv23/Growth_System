package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Raza implements Serializable{
	private static final long serialVersionUID = -6833167247955613395L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDRaza;
	
	@Column
	private String raza;
	
	@Column
	private String descripcion;

	public Raza() {
		super();
	}

	public Raza(Long iDRaza) {
		super();
		IDRaza = iDRaza;
	}

	public Long getIDRaza() {
		return IDRaza;
	}

	public void setIDRaza(Long iDRaza) {
		IDRaza = iDRaza;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Raza [IDRaza=" + IDRaza + ", raza=" + raza + ", descripcion=" + descripcion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDRaza == null) ? 0 : IDRaza.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((raza == null) ? 0 : raza.hashCode());
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
		Raza other = (Raza) obj;
		if (IDRaza == null) {
			if (other.IDRaza != null)
				return false;
		} else if (!IDRaza.equals(other.IDRaza))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (raza == null) {
			if (other.raza != null)
				return false;
		} else if (!raza.equals(other.raza))
			return false;
		return true;
	}
}
