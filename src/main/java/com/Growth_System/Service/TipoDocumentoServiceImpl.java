package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Tipo_documento;
import com.Growth_System.Repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {
	
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	@Override
	public List<Tipo_documento> listaTipoDoc() {
		return (List<Tipo_documento>) tipoDocumentoRepository.findAll();
	}

}
