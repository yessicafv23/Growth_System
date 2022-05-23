package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Estado_salud;
import com.Growth_System.Repository.EstadoSaludRepository;

@Service
public class EstadoSaludServiceImpl implements EstadoSaludService {
	
	@Autowired
	private EstadoSaludRepository estadoSaludRepository; 

	@Override
	public List<Estado_salud> listarTodos() {
		return (List<Estado_salud>) estadoSaludRepository.findAll();
	}

	@Override
	public void guardarEstado(Estado_salud estado_salud) {
		estadoSaludRepository.save(estado_salud);
	}

	@Override
	public Estado_salud buscarPorId(Long idEstadoSalud) {
		// TODO Auto-generated method stub
		return estadoSaludRepository.findById(idEstadoSalud).orElse(null);
	}

	@Override
	public void eliminarEstado(Long idEstadoSalud) {
		estadoSaludRepository.deleteById(idEstadoSalud);
	}

}
