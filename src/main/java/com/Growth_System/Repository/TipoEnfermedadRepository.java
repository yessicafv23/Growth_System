package com.Growth_System.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Growth_System.Entity.Tipo_Enfermedad;

@Repository
public interface TipoEnfermedadRepository extends CrudRepository <Tipo_Enfermedad, Long> {

}
