package com.Growth_System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Cargo;
import com.Growth_System.Repository.CargoRepository;

@Service
public class CargoServiceImpl implements CargoService {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Override
	public List<Cargo> listaCargo() {
		return (List<Cargo>) cargoRepository.findAll();
	}

}
