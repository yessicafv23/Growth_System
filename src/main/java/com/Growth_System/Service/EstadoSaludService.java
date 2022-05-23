package com.Growth_System.Service;

import java.util.List;

import com.Growth_System.Entity.Estado_salud;

public interface EstadoSaludService {
	
	public List<Estado_salud> listarTodos();
	
	public void guardarEstado(Estado_salud estado_salud);
	
	public Estado_salud buscarPorId(Long idEstadoSalud);
	
	public void eliminarEstado(Long idEstadoSalud);
}
