package com.Growth_System.Service;


import java.util.List;

import com.Growth_System.Entity.Prevencion;



public interface PrevencionService {
	
	
	public Prevencion buscarPorId(Long IDPrevencion);
	
	public Prevencion createPrevencion(Prevencion prev) throws Exception;
	
	//ACTUALIZAR DATOS
	public Prevencion updatePrevencion(Prevencion preven);
	
	//ELIMINAR DATOS (ESTADO)
	public void prevencionEstado (Prevencion preest);
	
	//REGISTRAR DATOS (LISTAR)
	public List <Prevencion> prevencionListado();



}
