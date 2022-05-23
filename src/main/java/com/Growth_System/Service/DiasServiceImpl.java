package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Dias;
import com.Growth_System.Repository.DiasRepository;

@Service
public class DiasServiceImpl implements DiasService{
	
	@Autowired
	DiasRepository diasRepository;

	@Override
	public List<Dias> listaDias() {
		return (List<Dias>) diasRepository.findAll();
	}

}
