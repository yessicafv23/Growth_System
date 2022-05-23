package com.Growth_System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Sintoma;
import com.Growth_System.Repository.SintomaRepository;

@Service
public class SintomaServiceImpl implements SintomaService {
	
	@Autowired
	SintomaRepository sintoma;
	


	@Override
	public Sintoma buscarPorId(Long IDSintoma) {
		return sintoma.findById(IDSintoma).orElse(null);
	}

	private boolean checkSintomaAvailable(Sintoma sint) throws Exception {
		Optional<Sintoma> userFound = sintoma.findByIDSintoma(sint.getIDSintoma());
		if (userFound.isPresent()) {
			throw new Exception("Sintoma ya registrado");
		}
		return true;
	}


	@Override
	public Sintoma createSintoma(Sintoma sint) throws Exception {
		
		if(checkSintomaAvailable(sint)){
			sint = sintoma.save(sint);
		}
		return null;
	}
		
		@Override
		public Sintoma updateSintoma(Sintoma sint) {
				
			sint = sintoma.save(sint);
				return null;
		}

		@Override
		public void sintomaEstado(Sintoma sinest) {

			try {
				Sintoma sinestobj = sintoma.findById(sinest.getIDSintoma()).orElse(null);
				sinestobj.setEstado(0);
				sintoma.save(sinestobj);
			}
			catch (Exception ex){
				
				System.out.println("El síntoma no fue encontrado!!");
			}
			
		}

		@Override
		public List<Sintoma> sintomaListado() {
			
			return sintoma.sintomaListado();
		}

	
	

}
