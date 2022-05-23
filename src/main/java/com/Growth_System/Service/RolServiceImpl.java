package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Rol;
import com.Growth_System.Repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {
	
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<Rol> listaRoles() {
		return (List<Rol>) rolRepository.findAll();
	}

}
