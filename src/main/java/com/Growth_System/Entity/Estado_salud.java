package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Estado_salud implements Serializable{
	private static final long serialVersionUID = -6833167247955613395L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDEstadoSalud;
	
	//Galpon
	@ManyToOne
	@JoinColumn(name="IDGalpon")
	private Galpon galpon;
	
	//Tipo de salud
	@ManyToOne
	@JoinColumn(name="IDTipo_salud")
	private Tipo_estadosalud tipo_estadosalud;
	
	@Column
	private Date fechaAsignacion;
	
	@Column
	private String descripcion;
	
	@Column
	private String estado;

	public Long getIdEstadoSalud() {
		return IDEstadoSalud;
	}

	public void setIdEstadoSalud(Long IDEstadoSalud) {
		this.IDEstadoSalud = IDEstadoSalud;
	}

	public Galpon getGalpon() {
		return galpon;
	}

	public void setGalpon(Galpon galpon) {
		this.galpon = galpon;
	}

	public Tipo_estadosalud getTipo_estadosalud() {
		return tipo_estadosalud;
	}

	public void setTipo_estadosalud(Tipo_estadosalud tipo_estadosalud) {
		this.tipo_estadosalud = tipo_estadosalud;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaAsignacion == null) ? 0 : fechaAsignacion.hashCode());
		result = prime * result + ((galpon == null) ? 0 : galpon.hashCode());
		result = prime * result + ((IDEstadoSalud == null) ? 0 : IDEstadoSalud.hashCode());
		result = prime * result + ((tipo_estadosalud == null) ? 0 : tipo_estadosalud.hashCode());
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
		Estado_salud other = (Estado_salud) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaAsignacion == null) {
			if (other.fechaAsignacion != null)
				return false;
		} else if (!fechaAsignacion.equals(other.fechaAsignacion))
			return false;
		if (galpon == null) {
			if (other.galpon != null)
				return false;
		} else if (!galpon.equals(other.galpon))
			return false;
		if (IDEstadoSalud == null) {
			if (other.IDEstadoSalud != null)
				return false;
		} else if (!IDEstadoSalud.equals(other.IDEstadoSalud))
			return false;
		if (tipo_estadosalud == null) {
			if (other.tipo_estadosalud != null)
				return false;
		} else if (!tipo_estadosalud.equals(other.tipo_estadosalud))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estado_salud [IDEstadoSalud=" + IDEstadoSalud + ", galpon=" + galpon + ", tipo_estadosalud="
				+ tipo_estadosalud + ", fechaAsignacion=" + fechaAsignacion + ", descripcion=" + descripcion
				+ ", estado=" + estado + "]";
	}

	
}

