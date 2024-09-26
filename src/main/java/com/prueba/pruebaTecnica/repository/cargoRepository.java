package com.prueba.pruebaTecnica.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prueba.pruebaTecnica.model.Cargo;
import com.prueba.pruebaTecnica.repository.repositoryCrud.CargoCrudRepository;

@Repository
public class cargoRepository {
	
	@Autowired
	private CargoCrudRepository cargoCrudRepository;
	
	public Iterable<Cargo> listadoCargo() {
		return cargoCrudRepository.findAll();
	}
}
