package com.Growth_System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Enfermedad;
import com.Growth_System.Repository.EnfermedadRepository;

@Service
public class EnfermedadServiceImpl implements EnfermedadService {
	
	@Autowired
	EnfermedadRepository enfermedades;

	@Override
	public Enfermedad buscarPorId(Long IDEnfermedad) {
		return enfermedades.findById(IDEnfermedad).orElse(null);
	}
/*
	@Override
	public Optional<Enfermedad>listarIDEnfermedad(Long IDEnfermedad){
		return enfermedades.findByIDEnfermedad(IDEnfermedad);
	}
*/
	private boolean checkEnfermedadAvailable(Enfermedad enfermedad) throws Exception {
		Optional<Enfermedad> userFound = enfermedades.findByIDEnfermedad(enfermedad.getIDEnfermedad());
		if (userFound.isPresent()) {
			throw new Exception("Enfermedad ya registrada");
		}
		return true;
	}


	@Override
	public Enfermedad createEnfermedad(Enfermedad enfermedad) throws Exception {
		
		if(checkEnfermedadAvailable(enfermedad)){
			enfermedad = enfermedades.save(enfermedad);
		}
		return null;
		
	}
		
		
	@Override
	public Enfermedad updateEnfermedad(Enfermedad enfermedad) {
			
			enfermedad = enfermedades.save(enfermedad);
			return null;
	}
	
	@Override
	public void enfermedadEstado(Enfermedad enfest) {
		
		try {
			Enfermedad enfestobj = enfermedades.findById(enfest.getIDEnfermedad()).orElse(null);
			enfestobj.setEstado(0);
			enfermedades.save(enfestobj);
		}
		catch (Exception ex){
			
			System.out.println("La enfermedad no fue encontrada!!");
		}
		
	}
	@Override
	public List<Enfermedad> enfermedadListado() {

		return enfermedades.enfermedadListado();
	}

	@Override
	public Long cantidadEnfermedades() {
		return enfermedades.cantidadEnfermedades();
	}


}
