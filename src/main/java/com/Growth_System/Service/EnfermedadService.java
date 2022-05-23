package com.Growth_System.Service;



import java.util.List;

import com.Growth_System.Entity.Enfermedad;





public interface EnfermedadService {
	
	
	
	public Enfermedad buscarPorId(Long IDEnfermedad);
	
	
	public Enfermedad createEnfermedad(Enfermedad enfermedad) throws Exception;
	
	//ACTUALIZAR DATOS
	public Enfermedad updateEnfermedad(Enfermedad enfermedad);
	
	//ELIMINAR DATOS (ESTADO)
	public void enfermedadEstado (Enfermedad enfest);
	
	//REGISTRAR DATOS (LISTAR)
	public List <Enfermedad> enfermedadListado();

	public Long cantidadEnfermedades();
}
