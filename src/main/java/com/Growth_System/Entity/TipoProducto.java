package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tipo_producto")
public class TipoProducto implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long IDTipo;

    @Column
    @NotEmpty
    private String nombreTipo;

    @Column
    private int activoTipo;

    @ManyToOne
    @JoinColumn(name = "p_idusuario")
    private Usuario pUsuario;

    public TipoProducto() {
    }

    public TipoProducto(Long IDTipo, String nombreTipo, int activoTipo, Usuario pUsuario) {
        this.IDTipo = IDTipo;
        this.nombreTipo = nombreTipo;
        this.activoTipo = activoTipo;
        this.pUsuario = pUsuario;
    }

    public Long getIDTipo() {
        return IDTipo;
    }

    public void setIDTipo(Long IDTipo) {
        this.IDTipo = IDTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public int getActivoTipo() {
        return activoTipo;
    }

    public void setActivoTipo(int activoTipo) {
        this.activoTipo = activoTipo;
    }

    public Usuario getpUsuario() {
        return pUsuario;
    }

    public void setpUsuario(Usuario pUsuario) {
        this.pUsuario = pUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoProducto that = (TipoProducto) o;
        return activoTipo == that.activoTipo && Objects.equals(IDTipo, that.IDTipo) && Objects.equals(nombreTipo, that.nombreTipo) && Objects.equals(pUsuario, that.pUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDTipo, nombreTipo, activoTipo, pUsuario);
    }

    @Override
    public String toString() {
        return "TipoProducto{" +
                "IDTipo=" + IDTipo +
                ", nombreTipo='" + nombreTipo + '\'' +
                ", activoTipo=" + activoTipo +
                ", pUsuario=" + pUsuario +
                '}';
    }
}
