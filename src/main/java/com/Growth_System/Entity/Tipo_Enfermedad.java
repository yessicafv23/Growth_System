package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Tipo_Enfermedad implements Serializable {
	
	private static final long serialVersionUID = -6833167247955613395L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDTipoEnfermedad;
	@Column
	private String NombreTipoEnfermedad;
	
	public Tipo_Enfermedad() {
		super();
	}

	public Tipo_Enfermedad(Long IDTipoEnfermedad) {
		super();
		this.IDTipoEnfermedad = IDTipoEnfermedad;
	}

	public Long getIDTipoEnfermedad() {
		return IDTipoEnfermedad;
	}

	public void setIDTipoEnfermedad(Long iDTipoEnfermedad) {
		IDTipoEnfermedad = iDTipoEnfermedad;
	}

	public String getNombreTipoEnfermedad() {
		return NombreTipoEnfermedad;
	}

	public void setNombreTipoEnfermedad(String nombreTipoEnfermedad) {
		NombreTipoEnfermedad = nombreTipoEnfermedad;
	}

	@Override
	public String toString() {
		return "Tipo_Enfermedad [IDTipoEnfermedad=" + IDTipoEnfermedad + ", NombreTipoEnfermedad="
				+ NombreTipoEnfermedad + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDTipoEnfermedad == null) ? 0 : IDTipoEnfermedad.hashCode());
		result = prime * result + ((NombreTipoEnfermedad == null) ? 0 : NombreTipoEnfermedad.hashCode());
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
		Tipo_Enfermedad other = (Tipo_Enfermedad) obj;
		if (IDTipoEnfermedad == null) {
			if (other.IDTipoEnfermedad != null)
				return false;
		} else if (!IDTipoEnfermedad.equals(other.IDTipoEnfermedad))
			return false;
		if (NombreTipoEnfermedad == null) {
			if (other.NombreTipoEnfermedad != null)
				return false;
		} else if (!NombreTipoEnfermedad.equals(other.NombreTipoEnfermedad))
			return false;
		return true;
	}
	
	

	
	
}