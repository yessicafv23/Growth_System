package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="inventario")
public class Inventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy = "native")
	private Long IDInventario;

	@Column
	@NotEmpty
	private String nombreInv;
	@Column
	private String descripcionInv;

	@ManyToOne
	@JoinColumn(name = "p_idusuario")
	private Usuario pUsuario;

	@Column
	private int activoInv;

	public Inventario() {

	}

	public Inventario(Long IDInventario, String nombreInv, String descripcionInv, Usuario pUsuario, int activoInv) {
		this.IDInventario = IDInventario;
		this.nombreInv = nombreInv;
		this.descripcionInv = descripcionInv;
		this.pUsuario = pUsuario;
		this.activoInv = activoInv;
	}

	public Long getIDInventario() {
		return IDInventario;
	}

	public void setIDInventario(Long IDInventario) {
		this.IDInventario = IDInventario;
	}

	public String getNombreInv() {
		return nombreInv;
	}

	public void setNombreInv(String nombreInv) {
		this.nombreInv = nombreInv;
	}

	public String getDescripcionInv() {
		return descripcionInv;
	}

	public void setDescripcionInv(String descripcionInv) {
		this.descripcionInv = descripcionInv;
	}

	public Usuario getpUsuario() {
		return pUsuario;
	}

	public void setpUsuario(Usuario pUsuario) {
		this.pUsuario = pUsuario;
	}

	public int getActivoInv() {
		return activoInv;
	}

	public void setActivoInv(int activoInv) {
		this.activoInv = activoInv;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Inventario that = (Inventario) o;
		return activoInv == that.activoInv && Objects.equals(IDInventario, that.IDInventario) && Objects.equals(nombreInv, that.nombreInv) && Objects.equals(descripcionInv, that.descripcionInv) && Objects.equals(pUsuario, that.pUsuario);
	}

	@Override
	public int hashCode() {
		return Objects.hash(IDInventario, nombreInv, descripcionInv, pUsuario, activoInv);
	}

	@Override
	public String toString() {
		return "Inventario{" +
				"IDInventario=" + IDInventario +
				", nombreInv='" + nombreInv + '\'' +
				", descripcionInv='" + descripcionInv + '\'' +
				", pUsuario=" + pUsuario +
				", activoInv=" + activoInv +
				'}';
	}
}
