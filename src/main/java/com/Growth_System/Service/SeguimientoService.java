package com.Growth_System.Service;

import java.util.List;

import com.Growth_System.Entity.Seguimiento_raza;

public interface SeguimientoService {
	public List<Seguimiento_raza> listarTodos();
	
	public void guardarSeguimiento(Seguimiento_raza seguimiento_raza);
	
	public Seguimiento_raza buscarPorId(Long idseguimiento_raza);
	
	public void eliminarSeguimiento (Long idseguimiento_raza);
}
