package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Tipo_Tratamiento implements Serializable {
	
	private static final long serialVersionUID = -6833167247955613395L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDTipoTratamiento;
	@Column
	private String NombreTipoTratamiento;
	
	public Tipo_Tratamiento() {
		super();
	}

	public Tipo_Tratamiento(Long IDTipoTratamiento) {
		super();
		this.IDTipoTratamiento = IDTipoTratamiento;
	}

	public Long getIDTipoTratamiento() {
		return IDTipoTratamiento;
	}

	public void setIDTipoTratamiento(Long iDTipoTratamiento) {
		IDTipoTratamiento = iDTipoTratamiento;
	}

	public String getNombreTipoTratamiento() {
		return NombreTipoTratamiento;
	}

	public void setNombreTipoTratamiento(String nombreTipoTratamiento) {
		NombreTipoTratamiento = nombreTipoTratamiento;
	}

	@Override
	public String toString() {
		return "Tipo_Tratamiento [IDTipoTratamiento=" + IDTipoTratamiento + ", NombreTipoTratamiento="
				+ NombreTipoTratamiento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDTipoTratamiento == null) ? 0 : IDTipoTratamiento.hashCode());
		result = prime * result + ((NombreTipoTratamiento == null) ? 0 : NombreTipoTratamiento.hashCode());
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
		Tipo_Tratamiento other = (Tipo_Tratamiento) obj;
		if (IDTipoTratamiento == null) {
			if (other.IDTipoTratamiento != null)
				return false;
		} else if (!IDTipoTratamiento.equals(other.IDTipoTratamiento))
			return false;
		if (NombreTipoTratamiento == null) {
			if (other.NombreTipoTratamiento != null)
				return false;
		} else if (!NombreTipoTratamiento.equals(other.NombreTipoTratamiento))
			return false;
		return true;
	}
	
	
}
