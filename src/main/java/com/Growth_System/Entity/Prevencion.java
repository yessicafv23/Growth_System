package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Prevencion implements Serializable {
	
	private static final long serialVersionUID = -6833167247955613395L;
	@Id
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDPrevencion;
	@Column
	private Date FechaRegistro;
	@Column
	private String Descripcion;
	@Column
	private String Recomendacion;
	
	//LLAVES FORANEAS
	
	@ManyToOne
	@JoinColumn(name="IDSintoma")
	private Sintoma IDSintoma;
	
	@Column
	private int Estado;
	
	public Prevencion() {
		super();
	}

	public Prevencion(Long IDPrevencion) {
		super();
		this.IDPrevencion = IDPrevencion;
	}

	public Long getIDPrevencion() {
		return IDPrevencion;
	}

	public void setIDPrevencion(Long iDPrevencion) {
		IDPrevencion = iDPrevencion;
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

	public String getRecomendacion() {
		return Recomendacion;
	}

	public void setRecomendacion(String recomendacion) {
		Recomendacion = recomendacion;
	}

	public Sintoma getIDSintoma() {
		return IDSintoma;
	}

	public void setIDSintoma(Sintoma iDSintoma) {
		IDSintoma = iDSintoma;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Prevencion [IDPrevencion=" + IDPrevencion + ", FechaRegistro=" + FechaRegistro + ", Descripcion="
				+ Descripcion + ", Recomendacion=" + Recomendacion + ", IDSintoma=" + IDSintoma + ", Estado=" + Estado
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Descripcion == null) ? 0 : Descripcion.hashCode());
		result = prime * result + Estado;
		result = prime * result + ((FechaRegistro == null) ? 0 : FechaRegistro.hashCode());
		result = prime * result + ((IDPrevencion == null) ? 0 : IDPrevencion.hashCode());
		result = prime * result + ((IDSintoma == null) ? 0 : IDSintoma.hashCode());
		result = prime * result + ((Recomendacion == null) ? 0 : Recomendacion.hashCode());
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
		Prevencion other = (Prevencion) obj;
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
		if (IDPrevencion == null) {
			if (other.IDPrevencion != null)
				return false;
		} else if (!IDPrevencion.equals(other.IDPrevencion))
			return false;
		if (IDSintoma == null) {
			if (other.IDSintoma != null)
				return false;
		} else if (!IDSintoma.equals(other.IDSintoma))
			return false;
		if (Recomendacion == null) {
			if (other.Recomendacion != null)
				return false;
		} else if (!Recomendacion.equals(other.Recomendacion))
			return false;
		return true;
	}

	
	
	
}