package com.Growth_System.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long IDProducto;

    @Column
    @NotEmpty(message = "Producto")
    private String nombreProd;

    @Column
    private String detalleProd;

    @Column
    private int existenciasProd;

    @Column
    @NotNull
    private int entradasProd;

    @Column
    private int salidasProd;

    @Column
    private int activoProd;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "p_idinventario")
    private Inventario pIdinventario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "p_idtipo")
    private TipoProducto pIdtipo;

    @ManyToOne
    @JoinColumn(name = "p_idusuario")
    private Usuario pUsuario;

    @ManyToOne
    @JoinColumn(name = "p_idusuariolast")
    private Usuario pUsuarioLast;

    public Producto() {
    }

    public Producto(Long IDProducto, String nombreProd, String detalleProd, int existenciasProd, int entradasProd, int salidasProd, int activoProd, Inventario pIdinventario, TipoProducto pIdtipo, Usuario pUsuario, Usuario pUsuarioLast) {
        this.IDProducto = IDProducto;
        this.nombreProd = nombreProd;
        this.detalleProd = detalleProd;
        this.existenciasProd = existenciasProd;
        this.entradasProd = entradasProd;
        this.salidasProd = salidasProd;
        this.activoProd = activoProd;
        this.pIdinventario = pIdinventario;
        this.pIdtipo = pIdtipo;
        this.pUsuario = pUsuario;
        this.pUsuarioLast = pUsuarioLast;
    }

    public Long getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(Long IDProducto) {
        this.IDProducto = IDProducto;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getDetalleProd() {
        return detalleProd;
    }

    public void setDetalleProd(String detalleProd) {
        this.detalleProd = detalleProd;
    }

    public int getExistenciasProd() {
        return existenciasProd;
    }

    public void setExistenciasProd(int existenciasProd) {
        this.existenciasProd = existenciasProd;
    }

    public int getEntradasProd() {
        return entradasProd;
    }

    public void setEntradasProd(int entradasProd) {
        this.entradasProd = entradasProd;
    }

    public int getSalidasProd() {
        return salidasProd;
    }

    public void setSalidasProd(int salidasProd) {
        this.salidasProd = salidasProd;
    }

    public int getActivoProd() {
        return activoProd;
    }

    public void setActivoProd(int activoProd) {
        this.activoProd = activoProd;
    }

    public Inventario getpIdinventario() {
        return pIdinventario;
    }

    public void setpIdinventario(Inventario pIdinventario) {
        this.pIdinventario = pIdinventario;
    }

    public TipoProducto getpIdtipo() {
        return pIdtipo;
    }

    public void setpIdtipo(TipoProducto pIdtipo) {
        this.pIdtipo = pIdtipo;
    }

    public Usuario getpUsuario() {
        return pUsuario;
    }

    public void setpUsuario(Usuario pUsuario) {
        this.pUsuario = pUsuario;
    }

    public Usuario getpUsuarioLast() {
        return pUsuarioLast;
    }

    public void setpUsuarioLast(Usuario pUsuarioLast) {
        this.pUsuarioLast = pUsuarioLast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return existenciasProd == producto.existenciasProd && entradasProd == producto.entradasProd && salidasProd == producto.salidasProd && activoProd == producto.activoProd && Objects.equals(IDProducto, producto.IDProducto) && Objects.equals(nombreProd, producto.nombreProd) && Objects.equals(detalleProd, producto.detalleProd) && Objects.equals(pIdinventario, producto.pIdinventario) && Objects.equals(pIdtipo, producto.pIdtipo) && Objects.equals(pUsuario, producto.pUsuario) && Objects.equals(pUsuarioLast, producto.pUsuarioLast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDProducto, nombreProd, detalleProd, existenciasProd, entradasProd, salidasProd, activoProd, pIdinventario, pIdtipo, pUsuario, pUsuarioLast);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "IDProducto=" + IDProducto +
                ", nombreProd='" + nombreProd + '\'' +
                ", detalleProd='" + detalleProd + '\'' +
                ", existenciasProd=" + existenciasProd +
                ", entradasProd=" + entradasProd +
                ", salidasProd=" + salidasProd +
                ", activoProd=" + activoProd +
                ", pIdinventario=" + pIdinventario +
                ", pIdtipo=" + pIdtipo +
                ", pUsuario=" + pUsuario +
                ", pUsuarioLast=" + pUsuarioLast +
                '}';
    }
}
