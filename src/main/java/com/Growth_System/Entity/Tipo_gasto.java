package com.Growth_System.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.Growth_System.Entity.Tipo_gasto;

@Entity
public class Tipo_gasto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDTipoGasto;
	
	@Column
	private String tipoGasto;
	
	@Column
	private String observacion;

	public Tipo_gasto() {
		super();
	}

	public Tipo_gasto(Long iDTipoGasto) {
		super();
		IDTipoGasto = iDTipoGasto;
	}

	public Long getIDTipoGasto() {
		return IDTipoGasto;
	}

	public void setIDTipoGasto(Long iDTipoGasto) {
		IDTipoGasto = iDTipoGasto;
	}

	public String getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public String toString() {
		return "Tipo_gasto [IDTipoGasto=" + IDTipoGasto + ", tipoGasto=" + tipoGasto + ", observacion=" + observacion
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDTipoGasto == null) ? 0 : IDTipoGasto.hashCode());
		result = prime * result + ((observacion == null) ? 0 : observacion.hashCode());
		result = prime * result + ((tipoGasto == null) ? 0 : tipoGasto.hashCode());
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
		Tipo_gasto other = (Tipo_gasto) obj;
		if (IDTipoGasto == null) {
			if (other.IDTipoGasto != null)
				return false;
		} else if (!IDTipoGasto.equals(other.IDTipoGasto))
			return false;
		if (observacion == null) {
			if (other.observacion != null)
				return false;
		} else if (!observacion.equals(other.observacion))
			return false;
		if (tipoGasto == null) {
			if (other.tipoGasto != null)
				return false;
		} else if (!tipoGasto.equals(other.tipoGasto))
			return false;
		return true;
	}
}
