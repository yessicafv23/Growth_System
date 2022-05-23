package com.Growth_System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Tratamiento;
import com.Growth_System.Repository.TratamientoRepository;

@Service
public class TratamientoServiceImpl implements TratamientoService {

	@Autowired
	TratamientoRepository tratamiento;
	
	
	
	@Override
	public Tratamiento buscarPorId(Long IDTratamiento) {
		return tratamiento.findById(IDTratamiento).orElse(null);
	}
	
	
	private boolean checkTratamientoAvailable(Tratamiento trat) throws Exception {
		Optional<Tratamiento> userFound = tratamiento.findByIDTratamiento(trat.getIDTratamiento());
		if (userFound.isPresent()) {
			throw new Exception("Tratamiento ya registrado");
		}
		return true;
	}


	@Override
	public Tratamiento createTratamiento(Tratamiento trat) throws Exception {
		
		if(checkTratamientoAvailable(trat)){
			trat = tratamiento.save(trat);
		}
		return null;

	}
	
	@Override
	public Tratamiento updateTratamiento(Tratamiento Tratmients) {
			
			Tratmients = tratamiento.save(Tratmients);
			return null;
	}

	@Override
	public void tratamientoEstado(Tratamiento tratest) {
		
		try {
			Tratamiento tratestobj = tratamiento.findById(tratest.getIDTratamiento()).orElse(null);
			tratestobj.setEstado(0);
			tratamiento.save(tratestobj);
		}
		catch (Exception ex){
			
			System.out.println("El tratamiento no fue encontrado!!");
		}
		
	}


	@Override
	public List<Tratamiento> tratamientoListado() {
		// TODO Auto-generated method stub
		return tratamiento.tratamientoListado();
	}

	@Override
	public Long cantidadTratamiento() {
		return tratamiento.cantidadTratamiento();
	}


}
	
