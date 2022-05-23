package com.Growth_System.Repository;

import com.Growth_System.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT * FROM producto WHERE activo_prod = 1", nativeQuery = true)
    List<Producto> listaProductos();

    @Query(value = "SELECT SUM(existencias_prod) FROM producto WHERE activo_prod = 1", nativeQuery = true)
    Long totalProducto();

    @Query(value = "SELECT SUM(salidas_prod) FROM producto WHERE activo_prod = 1", nativeQuery = true)
    Long salidasProductos();

    @Query(value = "SELECT nombre_prod, existencias_prod FROM growth_system.producto WHERE activo_prod = 1 order by existencias_prod desc LIMIT 5", nativeQuery = true)
    List<Object> topProductos();
}
