package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Seguimiento_raza;
import com.Growth_System.Repository.SeguimientoRepository;

@Service
public class SeguimientoServiceImpl implements SeguimientoService {
	
	@Autowired
	SeguimientoRepository seguimientoRepository;

	@Override
	public List<Seguimiento_raza> listarTodos() {
		return (List<Seguimiento_raza>) seguimientoRepository.findAll();
	}

	@Override
	public void guardarSeguimiento(Seguimiento_raza seguimiento_raza) {
		seguimientoRepository.save(seguimiento_raza);
		
	}

	@Override
	public Seguimiento_raza buscarPorId(Long idseguimiento_raza) {
		return seguimientoRepository.findById(idseguimiento_raza).orElse(null);
	}

	@Override
	public void eliminarSeguimiento(Long idseguimiento_raza) {
		seguimientoRepository.deleteById(idseguimiento_raza);	
	}

	

}
