package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Raza;
import com.Growth_System.Repository.RazaRepository;

@Service
public class RazaServiceImpl implements RazaService{
	
	@Autowired
	private RazaRepository razaRepository;
	
	@Override
	public List<Raza> listaRaza() {
		return (List<Raza>) razaRepository.findAll();
	}

}
