package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Galpon implements Serializable{
	private static final long serialVersionUID = -6833167247955613395L;
	
	@Id
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDGalpon;
	
	@Column
	private String nombreGalpon;
	
	@Column
	private Date fechaApertura;
	
	//IDRaza
	@ManyToOne
	@JoinColumn(name="IDRaza")
	private Raza raza;
	
	@Column
	private int cantidadPollos;
	
	//id_Usuario
	@ManyToOne
	@JoinColumn(name="IDUsuario")
	private Usuario usuario;
	
	@Column
	private String descripcion;
	
	@Column
	private String estadoGalpon;

	public Long getIDGalpon() {
		return IDGalpon;
	}

	public void setIDGalpon(Long iDGalpon) {
		IDGalpon = iDGalpon;
	}

	public String getNombreGalpon() {
		return nombreGalpon;
	}

	public void setNombreGalpon(String nombreGalpon) {
		this.nombreGalpon = nombreGalpon;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public int getCantidadPollos() {
		return cantidadPollos;
	}

	public void setCantidadPollos(int cantidadPollos) {
		this.cantidadPollos = cantidadPollos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoGalpon() {
		return estadoGalpon;
	}

	public void setEstadoGalpon(String estadoGalpon) {
		this.estadoGalpon = estadoGalpon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDGalpon == null) ? 0 : IDGalpon.hashCode());
		result = prime * result + cantidadPollos;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estadoGalpon == null) ? 0 : estadoGalpon.hashCode());
		result = prime * result + ((fechaApertura == null) ? 0 : fechaApertura.hashCode());
		result = prime * result + ((nombreGalpon == null) ? 0 : nombreGalpon.hashCode());
		result = prime * result + ((raza == null) ? 0 : raza.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Galpon other = (Galpon) obj;
		if (IDGalpon == null) {
			if (other.IDGalpon != null)
				return false;
		} else if (!IDGalpon.equals(other.IDGalpon))
			return false;
		if (cantidadPollos != other.cantidadPollos)
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estadoGalpon == null) {
			if (other.estadoGalpon != null)
				return false;
		} else if (!estadoGalpon.equals(other.estadoGalpon))
			return false;
		if (fechaApertura == null) {
			if (other.fechaApertura != null)
				return false;
		} else if (!fechaApertura.equals(other.fechaApertura))
			return false;
		if (nombreGalpon == null) {
			if (other.nombreGalpon != null)
				return false;
		} else if (!nombreGalpon.equals(other.nombreGalpon))
			return false;
		if (raza == null) {
			if (other.raza != null)
				return false;
		} else if (!raza.equals(other.raza))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Galpon [IDGalpon=" + IDGalpon + ", nombreGalpon=" + nombreGalpon + ", fechaApertura=" + fechaApertura
				+ ", raza=" + raza + ", cantidadPollos=" + cantidadPollos + ", usuario=" + usuario + ", descripcion="
				+ descripcion + ", estadoGalpon=" + estadoGalpon + "]";
	}
	
	
}
