package com.Growth_System.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Growth_System.Entity.Tipo_Tratamiento;



@Repository
public interface TipoTratamientoRepository extends CrudRepository <Tipo_Tratamiento, Long> {

}
