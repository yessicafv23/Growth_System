package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Tipo_estadosalud;
import com.Growth_System.Repository.TipoEstadoSaludRepository;

@Service
public class TipoEstadoSaludServiceImpl implements TipoEstadoSaludService {
	
	@Autowired
	private TipoEstadoSaludRepository tipoEstadoSaludRepository;
	
	@Override
	public List<Tipo_estadosalud> listaTipoSalud() {
		return (List<Tipo_estadosalud>) tipoEstadoSaludRepository.findAll();
	}

}
