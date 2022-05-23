package com.Growth_System.Repository;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.Growth_System.Entity.Gasto;

public interface GastoRepository extends CrudRepository<Gasto, Long> {
public Optional<Gasto> findByIDGasto(Long IDGasto);
	
	// Consulta
	@Query(value = "SELECT * FROM gasto WHERE estado_gasto = 'activo'", nativeQuery = true)
	public List <Gasto> listaGasto();

	@Query(value = "SELECT sum(importe) FROM growth_system.gasto WHERE estado_gasto = 'activo'", nativeQuery = true)
	public Long gatosTotal();

	@Query(value = "SELECT monthname(fecha_registro), sum(importe) FROM growth_system.gasto WHERE YEAR(fecha_registro) = ?1 AND estado_gasto = 'activo' group by month(fecha_registro) ORDER BY month(fecha_registro)", nativeQuery = true)
	public List<Object> gastosMesPorYear(int anio);
}
