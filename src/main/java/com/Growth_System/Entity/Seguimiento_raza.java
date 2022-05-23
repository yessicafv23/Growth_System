package com.Growth_System.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.Growth_System.Entity.Dias;
import com.Growth_System.Entity.Raza;
import com.Growth_System.Entity.Seguimiento_raza;

@Entity
public class Seguimiento_raza implements Serializable{
	private static final long serialVersionUID = -6833167247955613395L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDSeguimientoRaza;
	
	@Column
	private String descripcion;
	
	//dias
	@ManyToOne
	@JoinColumn(name="IDDias")
	private Dias dias;
	
	//raza
	@ManyToOne
	@JoinColumn(name="IDRaza")
	private Raza raza;
	
	@Column
	private String estado_seguimiento;

	public Seguimiento_raza() {
		super();
	}

	public Long getIdseguimiento_raza() {
		return IDSeguimientoRaza;
	}

	public void setIdseguimiento_raza(Long IDSeguimientoRaza) {
		this.IDSeguimientoRaza = IDSeguimientoRaza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Dias getDias() {
		return dias;
	}

	public void setDias(Dias dias) {
		this.dias = dias;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public String getEstado_seguimiento() {
		return estado_seguimiento;
	}

	public void setEstado_seguimiento(String estado_seguimiento) {
		this.estado_seguimiento = estado_seguimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((dias == null) ? 0 : dias.hashCode());
		result = prime * result + ((estado_seguimiento == null) ? 0 : estado_seguimiento.hashCode());
		result = prime * result + ((IDSeguimientoRaza == null) ? 0 : IDSeguimientoRaza.hashCode());
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
		Seguimiento_raza other = (Seguimiento_raza) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (dias == null) {
			if (other.dias != null)
				return false;
		} else if (!dias.equals(other.dias))
			return false;
		if (estado_seguimiento == null) {
			if (other.estado_seguimiento != null)
				return false;
		} else if (!estado_seguimiento.equals(other.estado_seguimiento))
			return false;
		if (IDSeguimientoRaza == null) {
			if (other.IDSeguimientoRaza != null)
				return false;
		} else if (!IDSeguimientoRaza.equals(other.IDSeguimientoRaza))
			return false;
		if (raza == null) {
			if (other.raza != null)
				return false;
		} else if (!raza.equals(other.raza))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seguimiento_raza [IDSeguimientoRaza=" + IDSeguimientoRaza + ", descripcion=" + descripcion + ", dias="
				+ dias + ", raza=" + raza + ", estado_seguimiento=" + estado_seguimiento + "]";
	}

}
