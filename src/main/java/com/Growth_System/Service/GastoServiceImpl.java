package com.Growth_System.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Growth_System.Entity.Gasto;
import com.Growth_System.Repository.GastoRepository;

@Service
public class GastoServiceImpl implements GastoService {

	@Autowired
	private GastoRepository gastoRepository;

	@Override
	public Gasto buscarPorId(Long IDGasto) {
		return gastoRepository.findById(IDGasto).orElse(null);
	}

	private boolean checkGastoAvailable(Gasto gasto) throws Exception {
		Optional<Gasto> userFound = gastoRepository.findByIDGasto(gasto.getIDGasto());
		if (userFound.isPresent()) {
			throw new Exception("El gasto ya está registrado");
		}
		return true;
	}

	@Override
	public Gasto registrarGasto(Gasto gasto) throws Exception {

		if (checkGastoAvailable(gasto)) {
			gasto = gastoRepository.save(gasto);
		}
		return null;

	}

	@Override
	public Gasto actualizarGasto(Gasto gasto) {
		gasto = gastoRepository.save(gasto);
		return null;
	}

	@Override
	public void eliminarGasto(Gasto EliminarGasto) {
		try {
			Gasto gastoobj = gastoRepository.findById(EliminarGasto.getIDGasto()).orElse(null);
			gastoobj.setEstadoGasto("inactivo");
			gastoRepository.save(gastoobj);
		} catch (Exception ex) {

			System.out.println("El gasto no se encuentra registrado.");
		}

	}

	@Override
	public List<Gasto> listaGasto() {
		return gastoRepository.listaGasto();
	}

	@Override
	public Long gatosTotal() {
		return gastoRepository.gatosTotal();
	}

	@Override
	public List<Object> gastosMesPorYear(int anio) {
		return gastoRepository.gastosMesPorYear(anio);
	}

}
