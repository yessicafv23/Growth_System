package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Tipo_estadosalud implements Serializable{
	private static final long serialVersionUID = -6833167247955613395L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDTipoSalud;
	
	@Column
	private String estado_salud;

	public Tipo_estadosalud() {
		super();
	}

	public Long getIdTipoSalud() {
		return IDTipoSalud;
	}

	public void setIdTipoSalud(Long IDTipoSalud) {
		this.IDTipoSalud = IDTipoSalud;
	}

	public String getEstado_salud() {
		return estado_salud;
	}

	public void setEstado_salud(String estado_salud) {
		this.estado_salud = estado_salud;
	}

	@Override
	public String toString() {
		return "Tipo_estadosalud [IDTipoSalud=" + IDTipoSalud + ", estado_salud=" + estado_salud + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado_salud == null) ? 0 : estado_salud.hashCode());
		result = prime * result + ((IDTipoSalud == null) ? 0 : IDTipoSalud.hashCode());
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
		Tipo_estadosalud other = (Tipo_estadosalud) obj;
		if (estado_salud == null) {
			if (other.estado_salud != null)
				return false;
		} else if (!estado_salud.equals(other.estado_salud))
			return false;
		if (IDTipoSalud == null) {
			if (other.IDTipoSalud != null)
				return false;
		} else if (!IDTipoSalud.equals(other.IDTipoSalud))
			return false;
		return true;
	}

}
