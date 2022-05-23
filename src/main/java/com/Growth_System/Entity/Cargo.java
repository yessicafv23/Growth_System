package com.Growth_System.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cargo implements Serializable{
	private static final long serialVersionUID = -6833167247955613395L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDCargo;
	
	@Column
	private String cargo;
	
	@Column
	private String descripcion;

	public Cargo() {
		super();
	}
	
	public Cargo(Long iDCargo) {
		super();
		IDCargo = iDCargo;
	}

	public Long getIDCargo() {
		return IDCargo;
	}

	public void setIDCargo(Long iDCargo) {
		this.IDCargo = iDCargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Cargo [IDCargo=" + IDCargo + ", cargo=" + cargo + ", descripcion=" + descripcion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((IDCargo == null) ? 0 : IDCargo.hashCode());
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
		Cargo other = (Cargo) obj;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (IDCargo == null) {
			if (other.IDCargo != null)
				return false;
		} else if (!IDCargo.equals(other.IDCargo))
			return false;
		return true;
	}

}
