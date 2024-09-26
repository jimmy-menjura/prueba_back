package com.prueba.pruebaTecnica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.pruebaTecnica.model.Cargo;
import com.prueba.pruebaTecnica.service.CargoService;

@RestController
@RequestMapping("/api")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	@GetMapping("/obtenerCargo")
	public Iterable<Cargo> obtenerCargo() {
		return cargoService.ListadoCargo();
	}
}
