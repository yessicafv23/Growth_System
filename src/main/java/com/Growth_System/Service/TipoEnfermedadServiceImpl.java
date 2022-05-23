package com.Growth_System.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Tipo_Enfermedad;
import com.Growth_System.Repository.TipoEnfermedadRepository;



@Service
public class TipoEnfermedadServiceImpl implements TipoEnfermedadService {
	
	@Autowired
	TipoEnfermedadRepository tipEnfermedad;
	
	@Override
	public Iterable<Tipo_Enfermedad> listar() {
		return (Iterable<Tipo_Enfermedad>) tipEnfermedad.findAll();
	}

}
