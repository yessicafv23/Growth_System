package com.Growth_System.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Growth_System.Entity.Tipo_Tratamiento;
import com.Growth_System.Repository.TipoTratamientoRepository;



@Service
public class TipoTratamientoServiceImpl implements TipoTratamientoService {
	
	@Autowired
	TipoTratamientoRepository tipTratamiento;
	
	@Override
	public Iterable<Tipo_Tratamiento> listar() {
		return (Iterable<Tipo_Tratamiento>) tipTratamiento.findAll();
	}

}
