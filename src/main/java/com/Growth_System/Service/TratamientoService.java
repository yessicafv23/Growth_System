package com.Growth_System.Service;


import java.util.List;

import com.Growth_System.Entity.Tratamiento;



public interface TratamientoService {

	public Tratamiento buscarPorId(Long IDTratamiento);
	
	public Tratamiento createTratamiento(Tratamiento trat) throws Exception;
	
	//ACTUALIZAR DATOS
	public Tratamiento updateTratamiento(Tratamiento Tratmients);
	
	//ELIMINAR DATOS (ESTADO)
	public void tratamientoEstado (Tratamiento tratest);
	
	//REGISTRAR DATOS (LISTAR)
	public List <Tratamiento> tratamientoListado();

	public Long cantidadTratamiento();
}
