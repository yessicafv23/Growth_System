package com.Growth_System.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.Growth_System.Entity.Tipo_documento;

@Entity
public class Tipo_documento implements Serializable{
	private static final long serialVersionUID = -2969524610059270447L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	
	private Long IDTipoDoc;
	
	@Column
	private String tipo_Doc;
	
	// Constructor
	public Tipo_documento() {
		super();
	}

	public Tipo_documento(Long iDTipoDoc) {
		super();
		this.IDTipoDoc = iDTipoDoc;
	}

	
	// Getters and Setters
	
	public Long getIDTipoDoc() {
		return IDTipoDoc;
	}

	public void setIDTipoDoc(Long iDTipoDoc) {
		this.IDTipoDoc = iDTipoDoc;
	}

	public String getTipo_Doc() {
		return tipo_Doc;
	}

	public void setTipo_Doc(String tipo_Doc) {
		this.tipo_Doc = tipo_Doc;
	}
	
	//toString

	@Override
	public String toString() {
		return "Tipo_documento [IDTipoDoc=" + IDTipoDoc + ", tipo_Doc=" + tipo_Doc + "]";
	}
	
	//hash

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDTipoDoc == null) ? 0 : IDTipoDoc.hashCode());
		result = prime * result + ((tipo_Doc == null) ? 0 : tipo_Doc.hashCode());
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
		Tipo_documento other = (Tipo_documento) obj;
		if (IDTipoDoc == null) {
			if (other.IDTipoDoc != null)
				return false;
		} else if (!IDTipoDoc.equals(other.IDTipoDoc))
			return false;
		if (tipo_Doc == null) {
			if (other.tipo_Doc != null)
				return false;
		} else if (!tipo_Doc.equals(other.tipo_Doc))
			return false;
		return true;
	}

}
