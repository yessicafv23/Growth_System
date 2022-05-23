package com.Growth_System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Growth_System.Entity.Galpon;
import com.Growth_System.Repository.GalponRepository;

@Service
public class GalponServiceImpl implements GalponService {
	@Autowired
	GalponRepository galponRepository;

	@Override
	public List<Galpon> listaGalpon() {
		return galponRepository.listaGalpon();
	}

	@Override
	public Long cantidadPollos() {
		return galponRepository.cantidadPollos();
	}

	@Override
	public Galpon buscarPorId(Long IDGalpon) {
		return galponRepository.findById(IDGalpon).orElse(null);
	}

	private boolean checkGalponAvailable(Galpon galpon) throws Exception {
		Optional<Galpon> userFound = galponRepository.findByIDGalpon(galpon.getIDGalpon());
		if (userFound.isPresent()) {
			throw new Exception("El galpón ya está registrado");
		}
		return true;
	}
	
	@Override
	public Galpon registrarGalpon(Galpon galpon) throws Exception {
		
		if(checkGalponAvailable(galpon)){
			galpon = galponRepository.save(galpon);
		}
		return null;
		
	}

	@Override
	public Galpon actualizarGalpon(Galpon galpon) {
		// TODO Auto-generated method stub
		galpon = galponRepository.save(galpon);
		return null;
	}

	@Override
	public void eliminarGalpon(Galpon EliminarGalpon) {
		// TODO Auto-generated method stub
		
		try {
			Galpon galponobj = galponRepository.findById(EliminarGalpon.getIDGalpon()).orElse(null);
			galponobj.setEstadoGalpon("inactivo");
			galponRepository.save(galponobj);
		}
		catch (Exception ex){
			
			System.out.println("El galpón no se encuentra");
		}
		
	}
	

}
