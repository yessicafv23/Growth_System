package com.Growth_System.Repository;

import com.Growth_System.Entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    @Query(value = "SELECT * FROM inventario WHERE activo_inv = 1", nativeQuery = true)
    List<Inventario> listaInventarios();
}
