package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Tratamiento implements Serializable {
	
	private static final long serialVersionUID = -6833167247955613395L;
	@Id
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDTratamiento;
	@Column
	private String nombreTratamiento;
	@Column
	private String Descripcion;

	
	//LLAVES FORANEAS
	
	@ManyToOne
	@JoinColumn(name="IDTipo_tratamiento")
	private Tipo_Tratamiento IDTipoTratamiento;
	
	//#2
	@ManyToOne
	@JoinColumn(name="IDEnfermedad")
	private Enfermedad IDEnfermedad;
	
	//#3
	@ManyToOne
	@JoinColumn(name="IDGalpon")
	private Galpon IDGalpon;
	
	
	@Column
	private int Estado;
	
	
	public Long getIDTratamiento() {
		return IDTratamiento;
	}
	public void setIDTratamiento(Long iDTratamiento) {
		IDTratamiento = iDTratamiento;
	}
	public String getNombreTratamiento() {
		return nombreTratamiento;
	}
	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public Tipo_Tratamiento getIDTipoTratamiento() {
		return IDTipoTratamiento;
	}
	public void setIDTipoTratamiento(Tipo_Tratamiento iDTipoTratamiento) {
		IDTipoTratamiento = iDTipoTratamiento;
	}
	public Enfermedad getIDEnfermedad() {
		return IDEnfermedad;
	}
	public void setIDEnfermedad(Enfermedad iDEnfermedad) {
		IDEnfermedad = iDEnfermedad;
	}
	
	public Galpon getIDGalpon() {
		return IDGalpon;
	}

	public void setIDGalpon(Galpon iDGalpon) {
		IDGalpon = iDGalpon;
	}
	public int getEstado() {
		return Estado;
	}
	public void setEstado(int estado) {
		Estado = estado;
	}
	@Override
	public String toString() {
		return "Tratamiento [IDTratamiento=" + IDTratamiento + ", nombreTratamiento=" + nombreTratamiento
				+ ", Descripcion=" + Descripcion + ", IDTipoTratamiento=" + IDTipoTratamiento + ", IDEnfermedad="
				+ IDEnfermedad + ", IDGalpon=" + IDGalpon + ", Estado=" + Estado + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Descripcion == null) ? 0 : Descripcion.hashCode());
		result = prime * result + Estado;
		result = prime * result + ((IDEnfermedad == null) ? 0 : IDEnfermedad.hashCode());
		result = prime * result + ((IDGalpon == null) ? 0 : IDGalpon.hashCode());
		result = prime * result + ((IDTipoTratamiento == null) ? 0 : IDTipoTratamiento.hashCode());
		result = prime * result + ((IDTratamiento == null) ? 0 : IDTratamiento.hashCode());
		result = prime * result + ((nombreTratamiento == null) ? 0 : nombreTratamiento.hashCode());
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
		Tratamiento other = (Tratamiento) obj;
		if (Descripcion == null) {
			if (other.Descripcion != null)
				return false;
		} else if (!Descripcion.equals(other.Descripcion))
			return false;
		if (Estado != other.Estado)
			return false;
		if (IDEnfermedad == null) {
			if (other.IDEnfermedad != null)
				return false;
		} else if (!IDEnfermedad.equals(other.IDEnfermedad))
			return false;
		if (IDGalpon == null) {
			if (other.IDGalpon != null)
				return false;
		} else if (!IDGalpon.equals(other.IDGalpon))
			return false;
		if (IDTipoTratamiento == null) {
			if (other.IDTipoTratamiento != null)
				return false;
		} else if (!IDTipoTratamiento.equals(other.IDTipoTratamiento))
			return false;
		if (IDTratamiento == null) {
			if (other.IDTratamiento != null)
				return false;
		} else if (!IDTratamiento.equals(other.IDTratamiento))
			return false;
		if (nombreTratamiento == null) {
			if (other.nombreTratamiento != null)
				return false;
		} else if (!nombreTratamiento.equals(other.nombreTratamiento))
			return false;
		return true;
	}

	
	
	
	
	
}