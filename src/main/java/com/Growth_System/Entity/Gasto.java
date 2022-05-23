package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Gasto implements Serializable{
	private static final long serialVersionUID = -6833167247955613395L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDGasto;
	
	@Column
	private Date fechaRegistro;
	
	//Galpon
	@ManyToOne
	@JoinColumn(name="IDGalpon")
	private Galpon galpon;
	
	// Gasto
	@ManyToOne
	@JoinColumn(name="IDTipo_gasto")
	private Tipo_gasto tipo_gasto;
	
	@Column
	private int importe;
	
	@Column
	private String observacion;
	
	@Column()
	private String estadoGasto;
	
	

	public Long getIDGasto() {
		return IDGasto;
	}

	public void setIDGasto(Long iDGasto) {
		IDGasto = iDGasto;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Galpon getGalpon() {
		return galpon;
	}


	public void setGalpon(Galpon galpon) {
		this.galpon = galpon;
	}



	public Tipo_gasto getTipo_gasto() {
		return tipo_gasto;
	}



	public void setTipo_gasto(Tipo_gasto tipo_gasto) {
		this.tipo_gasto = tipo_gasto;
	}



	public int getImporte() {
		return importe;
	}



	public void setImporte(int importe) {
		this.importe = importe;
	}



	public String getObservacion() {
		return observacion;
	}



	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	public String getEstadoGasto() {
		return estadoGasto;
	}



	public void setEstadoGasto(String estadoGasto) {
		this.estadoGasto = estadoGasto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDGasto == null) ? 0 : IDGasto.hashCode());
		result = prime * result + ((estadoGasto == null) ? 0 : estadoGasto.hashCode());
		result = prime * result + ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result + ((galpon == null) ? 0 : galpon.hashCode());
		result = prime * result + importe;
		result = prime * result + ((observacion == null) ? 0 : observacion.hashCode());
		result = prime * result + ((tipo_gasto == null) ? 0 : tipo_gasto.hashCode());
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
		Gasto other = (Gasto) obj;
		if (IDGasto == null) {
			if (other.IDGasto != null)
				return false;
		} else if (!IDGasto.equals(other.IDGasto))
			return false;
		if (estadoGasto == null) {
			if (other.estadoGasto != null)
				return false;
		} else if (!estadoGasto.equals(other.estadoGasto))
			return false;
		if (fechaRegistro == null) {
			if (other.fechaRegistro != null)
				return false;
		} else if (!fechaRegistro.equals(other.fechaRegistro))
			return false;
		if (galpon == null) {
			if (other.galpon != null)
				return false;
		} else if (!galpon.equals(other.galpon))
			return false;
		if (importe != other.importe)
			return false;
		if (observacion == null) {
			if (other.observacion != null)
				return false;
		} else if (!observacion.equals(other.observacion))
			return false;
		if (tipo_gasto == null) {
			if (other.tipo_gasto != null)
				return false;
		} else if (!tipo_gasto.equals(other.tipo_gasto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gasto [IDGasto=" + IDGasto + ", fechaRegistro=" + fechaRegistro + ", galpon=" + galpon + ", tipo_gasto="
				+ tipo_gasto + ", importe=" + importe + ", observacion=" + observacion + ", estadoGasto=" + estadoGasto
				+ "]";
	}

	
	
}

