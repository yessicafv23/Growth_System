package com.Growth_System.Entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -2969524610059270447L;
	
	@Id
	@GenericGenerator(name="native",strategy="native")
	private Long IDUsuario;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	//id_TipoDoc
	@ManyToOne
	@JoinColumn(name="IDTipo_doc")
	private Tipo_documento tipo_documento;

	@Column
	private String username;

	@Column
	private String password;
	
	@Column
	private long telefono;
	
	//id_Ciudad
	@ManyToOne
	@JoinColumn(name="IDCiudad")
	private Ciudad ciudad;

	@ManyToOne
	@JoinColumn(name="IDCargo")
	private Cargo cargo;
	
	@Column
	private int estadoUsu;

	@Column
	private String rol;

	@Column
	private String claveConfirmacion;

	private boolean enabled;

	public Usuario() {
		super();
	}
	
	public Usuario(Long IDUsuario) {
		super();
		this.IDUsuario = IDUsuario;
	}

	public Long getIDUsuario() {
		return IDUsuario;
	}

	public void setIDUsuario(Long IDUsuario) {
		this.IDUsuario = IDUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Tipo_documento getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(Tipo_documento tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public int getEstadoUsu() {
		return estadoUsu;
	}

	public void setEstadoUsu(int estadoUsu) {
		this.estadoUsu = estadoUsu;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getClaveConfirmacion() {
		return claveConfirmacion;
	}

	public void setClaveConfirmacion(String claveConfirmacion) {
		this.claveConfirmacion = claveConfirmacion;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return telefono == usuario.telefono && estadoUsu == usuario.estadoUsu && enabled == usuario.enabled && Objects.equals(IDUsuario, usuario.IDUsuario) && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellido, usuario.apellido) && Objects.equals(tipo_documento, usuario.tipo_documento) && Objects.equals(username, usuario.username) && Objects.equals(password, usuario.password) && Objects.equals(ciudad, usuario.ciudad) && Objects.equals(cargo, usuario.cargo) && Objects.equals(rol, usuario.rol) && Objects.equals(claveConfirmacion, usuario.claveConfirmacion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(IDUsuario, nombre, apellido, tipo_documento, username, password, telefono, ciudad, cargo, estadoUsu, rol, claveConfirmacion, enabled);
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"IDUsuario=" + IDUsuario +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", tipo_documento=" + tipo_documento +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", telefono=" + telefono +
				", ciudad=" + ciudad +
				", cargo=" + cargo +
				", estadoUsu=" + estadoUsu +
				", rol='" + rol + '\'' +
				", claveConfirmacion='" + claveConfirmacion + '\'' +
				", enabled=" + enabled +
				'}';
	}
}
