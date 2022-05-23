package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Tipo_gasto;
import com.Growth_System.Repository.TipoGastoRepository;

@Service
public class TipoGastoServiceImpl implements TipoGastoService{
	
	@Autowired
	private TipoGastoRepository tipoGastoRepository;
	
	@Override
	public List<Tipo_gasto> listaGastos() {
		return (List<Tipo_gasto>) tipoGastoRepository.findAll();
	}

}
