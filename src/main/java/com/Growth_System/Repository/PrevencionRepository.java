package com.Growth_System.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Growth_System.Entity.Prevencion;



@Repository
public interface PrevencionRepository extends  CrudRepository<Prevencion, Long> {
	
	public Optional<Prevencion> findByIDPrevencion(Long IDPrevencion);
	
	@Query(value = "SELECT * FROM prevencion WHERE estado = 1", nativeQuery = true)
	
	public List <Prevencion> prevencionListado();


}
