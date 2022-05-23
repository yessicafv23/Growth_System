package com.Growth_System.Service;


import java.util.List;

import com.Growth_System.Entity.Sintoma;




public interface SintomaService {
	
	
	public Sintoma buscarPorId(Long IDSintoma);
	
	public Sintoma createSintoma(Sintoma sint) throws Exception;
	
	//ACTUALIZAR DATOS
	public Sintoma updateSintoma(Sintoma sintma);

	//ELIMINAR DATOS (ESTADO)
	public void sintomaEstado (Sintoma sinest);
	
	//REGISTRAR DATOS (LISTAR)
	public List <Sintoma> sintomaListado();
	

}
