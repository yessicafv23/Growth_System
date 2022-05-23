package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
public class Sintoma implements Serializable {
	
	private static final long serialVersionUID = -6833167247955613395L;
	@Id
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDSintoma;
	@Column
	private Date FechaRegistro;
	@Column
	private String Descripcion;
	
	//LLAVES FORANEAS
	
	@ManyToOne
	@JoinColumn(name="IDGalpon")
	private Galpon IDGalpon;
	
	//#2
	@ManyToOne
	@JoinColumn(name="IDEnfermedad")
	private Enfermedad IDEnfermedad;
	
	@Column
	private int Estado;
	
	public Sintoma() {
		super();
	}

	public Sintoma(Long IDSintoma) {
		super();
		this.IDSintoma = IDSintoma;
	}

	public Long getIDSintoma() {
		return IDSintoma;
	}

	public void setIDSintoma(Long iDSintoma) {
		IDSintoma = iDSintoma;
	}

	public Date getFechaRegistro() {
		return FechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Galpon getIDGalpon() {
		return IDGalpon;
	}

	public void setIDGalpon(Galpon iDGalpon) {
		IDGalpon = iDGalpon;
	}

	public Enfermedad getIDEnfermedad() {
		return IDEnfermedad;
	}

	public void setIDEnfermedad(Enfermedad iDEnfermedad) {
		IDEnfermedad = iDEnfermedad;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Sintoma [IDSintoma=" + IDSintoma + ", FechaRegistro=" + FechaRegistro + ", Descripcion=" + Descripcion
				+ ", IDGalpon=" + IDGalpon + ", IDEnfermedad=" + IDEnfermedad + ", Estado=" + Estado + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Descripcion == null) ? 0 : Descripcion.hashCode());
		result = prime * result + Estado;
		result = prime * result + ((FechaRegistro == null) ? 0 : FechaRegistro.hashCode());
		result = prime * result + ((IDEnfermedad == null) ? 0 : IDEnfermedad.hashCode());
		result = prime * result + ((IDGalpon == null) ? 0 : IDGalpon.hashCode());
		result = prime * result + ((IDSintoma == null) ? 0 : IDSintoma.hashCode());
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
		Sintoma other = (Sintoma) obj;
		if (Descripcion == null) {
			if (other.Descripcion != null)
				return false;
		} else if (!Descripcion.equals(other.Descripcion))
			return false;
		if (Estado != other.Estado)
			return false;
		if (FechaRegistro == null) {
			if (other.FechaRegistro != null)
				return false;
		} else if (!FechaRegistro.equals(other.FechaRegistro))
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
		if (IDSintoma == null) {
			if (other.IDSintoma != null)
				return false;
		} else if (!IDSintoma.equals(other.IDSintoma))
			return false;
		return true;
	}


	
	
	
	
}