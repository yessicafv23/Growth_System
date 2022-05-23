package com.Growth_System.Service;

import java.util.List;

import com.Growth_System.Entity.Galpon;

public interface GalponService {
	
	public Galpon buscarPorId(Long IDGalpon);
	
	public Galpon registrarGalpon(Galpon galpon) throws Exception;
	
	public Galpon actualizarGalpon(Galpon galpon);
	
	public void eliminarGalpon(Galpon EliminarGalpon);
	
	List<Galpon> listaGalpon();

	public Long cantidadPollos();
	
}
