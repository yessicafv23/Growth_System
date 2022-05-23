package com.Growth_System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Prevencion;
import com.Growth_System.Repository.PrevencionRepository;


@Service
public class PrevencionServiceImpl implements PrevencionService {
	
	@Autowired
	PrevencionRepository prevencion;
	
	

	@Override
	public Prevencion buscarPorId(Long IDPrevencion) {
		return prevencion.findById(IDPrevencion).orElse(null);
	}

	private boolean checkPrevencionAvailable(Prevencion prev) throws Exception {
		Optional<Prevencion> userFound = prevencion.findByIDPrevencion(prev.getIDPrevencion());
		if (userFound.isPresent()) {
			throw new Exception("Prevencion ya registrada");
		}
		return true;
	}


	@Override
	public Prevencion createPrevencion(Prevencion prev) throws Exception {
		
		if(checkPrevencionAvailable(prev)){
			prev = prevencion.save(prev);
		}
		return null;

	}
	
	@Override
	public Prevencion updatePrevencion(Prevencion preven) {
		
		preven = prevencion.save(preven);
		return null;
	}

	@Override
	public void prevencionEstado(Prevencion preest) {
		
		try {
			Prevencion preestobj = prevencion.findById(preest.getIDPrevencion()).orElse(null);
			preestobj.setEstado(0);
			prevencion.save(preestobj);
		}
		catch (Exception ex){
			
			System.out.println("La prevención no fue encontrada!!");
		}
		
	}

	@Override
	public List<Prevencion> prevencionListado() {

		return prevencion.prevencionListado();
	}
	
	



}
