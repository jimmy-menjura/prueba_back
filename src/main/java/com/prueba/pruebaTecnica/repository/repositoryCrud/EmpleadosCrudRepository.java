package com.prueba.pruebaTecnica.repository.repositoryCrud;


import org.springframework.data.repository.CrudRepository;

import com.prueba.pruebaTecnica.model.Empleados;

public interface EmpleadosCrudRepository extends CrudRepository<Empleados, Integer> {
	
}
