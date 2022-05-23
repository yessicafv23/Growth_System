package com.Growth_System.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Growth_System.Entity.Tratamiento;


@Repository
public interface TratamientoRepository extends CrudRepository<Tratamiento, Long> {
	
	public Optional<Tratamiento> findByIDTratamiento(Long IDTratamiento);
	
	@Query(value = "SELECT * FROM tratamiento WHERE estado = 1", nativeQuery = true)
	public List <Tratamiento> tratamientoListado();

	@Query(value = "SELECT COUNT(IDTratamiento) FROM tratamiento WHERE estado = 1", nativeQuery = true)
	public Long cantidadTratamiento();

}
