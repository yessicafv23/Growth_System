package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Enfermedad implements Serializable {
	
	private static final long serialVersionUID = -6833167247955613395L;
	@Id
	@GenericGenerator(name="native",strategy="native")

	private Long IDEnfermedad;
	@Column
	private String Nombre;
	@Column
	private String Descripcion;
	
	//LLAVES FORANEAS
	
	@ManyToOne
	@JoinColumn(name="IDTipo_enfermedad")
	private Tipo_Enfermedad IDTipoEnfermedad;
	
	@Column
	private int Estado;
	
	public Enfermedad() {
		super();
	}

	public Enfermedad(Long IDEnfermedad) {
		super();
		this.IDEnfermedad = IDEnfermedad;
	}

	public Long getIDEnfermedad() {
		return IDEnfermedad;
	}

	public void setIDEnfermedad(Long iDEnfermedad) {
		IDEnfermedad = iDEnfermedad;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Tipo_Enfermedad getIDTipoEnfermedad() {
		return IDTipoEnfermedad;
	}

	public void setIDTipoEnfermedad(Tipo_Enfermedad iDTipoEnfermedad) {
		IDTipoEnfermedad = iDTipoEnfermedad;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Enfermedad [IDEnfermedad=" + IDEnfermedad + ", Nombre=" + Nombre + ", Descripcion=" + Descripcion
				+ ", IDTipoEnfermedad=" + IDTipoEnfermedad + ", Estado=" + Estado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Descripcion == null) ? 0 : Descripcion.hashCode());
		result = prime * result + Estado;
		result = prime * result + ((IDEnfermedad == null) ? 0 : IDEnfermedad.hashCode());
		result = prime * result + ((IDTipoEnfermedad == null) ? 0 : IDTipoEnfermedad.hashCode());
		result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
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
		Enfermedad other = (Enfermedad) obj;
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
		if (IDTipoEnfermedad == null) {
			if (other.IDTipoEnfermedad != null)
				return false;
		} else if (!IDTipoEnfermedad.equals(other.IDTipoEnfermedad))
			return false;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		return true;
	}

	





	
	


}