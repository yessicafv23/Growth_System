package com.Growth_System.Service;

import com.Growth_System.Entity.Inventario;
import com.Growth_System.Entity.TipoProducto;

import java.util.List;

public interface InventarioService {
    List<Inventario> getAllInventarios();

    void guardar(Inventario inventario);

    void eliminar(Inventario inventario);

    public Inventario encontrarInventario(Inventario inventario);

    public boolean hayProducto(Inventario inventario);

    public void cambiarActivo(Inventario inventario);

}
