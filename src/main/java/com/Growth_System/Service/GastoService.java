package com.Growth_System.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import com.Growth_System.Entity.Gasto;

public interface GastoService {
	public Gasto buscarPorId(Long IDGasto);
	
	public Gasto registrarGasto(Gasto gasto) throws Exception;
	
	public Gasto actualizarGasto(Gasto gasto);
	
	public void eliminarGasto(Gasto EliminarGasto);
	
	List<Gasto> listaGasto();

	public Long gatosTotal();

	public List<Object> gastosMesPorYear(int anio);

}
