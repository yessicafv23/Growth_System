package com.Growth_System.Repository;

import com.Growth_System.Entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Long> {

    @Query(value = "SELECT * FROM tipo_producto WHERE activo_tipo = 1", nativeQuery = true)
    List<TipoProducto> listaTipoProductos();
}
