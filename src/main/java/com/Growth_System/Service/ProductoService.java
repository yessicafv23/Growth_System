package com.Growth_System.Service;

import com.Growth_System.Entity.Producto;

import java.util.ArrayList;
import java.util.List;

public interface ProductoService {

    List<Producto> listaProductos();

    void guardar(Producto producto);

    void eliminar(Producto producto);

    public Producto encontrarProducto(Producto producto);

    public boolean hayProducto(Producto producto);

    public void cambiarActivo(Producto producto);

    public Long totalProductos();

    public Long salidasProductos();

    public List<Object> topProductos();
}
