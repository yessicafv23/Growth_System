package com.Growth_System.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Growth_System.Entity.Raza;

@Repository
public interface RazaRepository extends CrudRepository<Raza, Long>{
	
}
