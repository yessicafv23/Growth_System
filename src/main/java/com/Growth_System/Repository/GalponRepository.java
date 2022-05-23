package com.Growth_System.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Growth_System.Entity.Galpon;


@Repository
public interface GalponRepository extends CrudRepository<Galpon, Long> {
	
	public Optional<Galpon> findByIDGalpon(Long IDGalpon);
	
	// Consulta
	@Query(value = "SELECT * FROM galpon WHERE estado_galpon = 'produccion'", nativeQuery = true)
	public List <Galpon> listaGalpon();

	@Query(value = "SELECT SUM(cantidad_pollos) FROM growth_system.galpon WHERE estado_galpon = 'produccion'" , nativeQuery = true)
	public Long cantidadPollos();
	
}
