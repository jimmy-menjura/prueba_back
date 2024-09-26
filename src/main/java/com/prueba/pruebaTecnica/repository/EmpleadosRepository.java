package com.prueba.pruebaTecnica.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prueba.pruebaTecnica.model.Empleados;
import com.prueba.pruebaTecnica.repository.repositoryCrud.EmpleadosCrudRepository;


@Repository
public class EmpleadosRepository {
	@Autowired
	private EmpleadosCrudRepository empleadosCrudRepository;
	
	public Iterable<Empleados> listadoEmpleados() {
		return empleadosCrudRepository.findAll();
	}
	public Optional<Empleados> listaEmpleado(int id) {
		return empleadosCrudRepository.findById(id);
	}
	public Empleados crearEmpleado(int cedula,
								   String nombre,
								   String foto,
								   Date fecha,
								   int cargo){
		Empleados empleados = new Empleados(cedula,
											nombre,
											foto,
											fecha,
											cargo);
		return empleadosCrudRepository.save(empleados);
	}

	
	public Optional<Empleados> obtenerEmpleadoPorId(int id){
		return empleadosCrudRepository.findById(id);
	}
	public boolean eliminarEmpleado(int id) {
		empleadosCrudRepository.deleteById(id);
		return true;
	}
	public Empleados actualizarEmpleado(Empleados empleado){
		return empleadosCrudRepository.save(empleado);
		
	}
}
