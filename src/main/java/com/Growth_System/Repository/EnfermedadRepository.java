package com.Growth_System.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Growth_System.Entity.Enfermedad;

@Repository
public interface EnfermedadRepository extends CrudRepository<Enfermedad, Long> {
	
	public Optional<Enfermedad> findByIDEnfermedad(Long IDEnfermedad);
	
	@Query(value = "SELECT * FROM enfermedad WHERE estado = 1", nativeQuery = true)
	public List <Enfermedad> enfermedadListado();

	@Query(value = "SELECT COUNT(IDEnfermedad) FROM enfermedad WHERE estado = 1", nativeQuery = true)
	public Long cantidadEnfermedades();
}
