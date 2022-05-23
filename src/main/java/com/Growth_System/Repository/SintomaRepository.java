package com.Growth_System.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Growth_System.Entity.Sintoma;



public interface SintomaRepository extends CrudRepository<Sintoma, Long> {
	
	public Optional<Sintoma> findByIDSintoma(Long IDSintoma);
	
	@Query(value = "SELECT * FROM sintoma WHERE estado = 1", nativeQuery = true)
	
	public List <Sintoma> sintomaListado();

}
