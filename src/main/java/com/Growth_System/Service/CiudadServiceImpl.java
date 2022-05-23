package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Ciudad;
import com.Growth_System.Repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements CiudadService {
	
	@Autowired
	private CiudadRepository ciudadRepository;
	
	@Override
	public List<Ciudad> listaCiudades() {
		return (List<Ciudad>) ciudadRepository.findAll();
	}

}
