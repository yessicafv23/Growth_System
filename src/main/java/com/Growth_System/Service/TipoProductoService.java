package com.Growth_System.Service;

import com.Growth_System.Entity.TipoProducto;

import java.util.List;

public interface TipoProductoService {
    List<TipoProducto> getAllTipoP();

    void guardar(TipoProducto tipo);

    void eliminar(TipoProducto tipo);

    public TipoProducto encontrarTipo(TipoProducto tipo);

    public boolean hayProducto(TipoProducto tipoProducto);

    public void cambiarActivo(TipoProducto tipoProducto);
}
