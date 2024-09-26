package com.prueba.pruebaTecnica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.pruebaTecnica.model.Cargo;
import com.prueba.pruebaTecnica.repository.cargoRepository;

@Service
public class CargoService {
	
	@Autowired
	private cargoRepository cargoRepository;
	
	public Iterable<Cargo> ListadoCargo() {
		return  cargoRepository.listadoCargo();
	}
}
