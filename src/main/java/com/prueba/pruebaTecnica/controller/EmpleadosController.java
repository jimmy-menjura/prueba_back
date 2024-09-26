package com.prueba.pruebaTecnica.controller;


import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.prueba.pruebaTecnica.model.Empleados;
import com.prueba.pruebaTecnica.service.EmpleadoService;




@RestController
@RequestMapping("/api")
public class EmpleadosController {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/obtenerEmpleados")
	public Iterable<Empleados> obtenerEmpleados() {
		return empleadoService.ListadoEmpleados();
	}
	@GetMapping("/obtenerEmpleado/{id}")
	public ResponseEntity<?> obtenerEmpleado(@PathVariable int id) {
		return ResponseEntity.ok().body(empleadoService.ListaEmpleado(id));
	}
	@PostMapping("/crearEmpleado")
	public ResponseEntity<?> crearEmpleado(@RequestParam ("cedula") int cedula,
										   @RequestParam ("nombre") String nombre,
										   @RequestParam ("foto") MultipartFile foto,
										   @RequestParam ("fecha") Date fecha,
										   @RequestParam ("cargo") int cargo) throws IOException {
	String urlImagen = empleadoService.guardarImagen(foto);
	return ResponseEntity.ok().body(empleadoService.CrearEmpleado(cedula,nombre,urlImagen,fecha,cargo));
	}
	@PutMapping("/actualizarEmpleado/{id}")
	public Empleados actualizarEmpleado(
			   @PathVariable int id,
			   @RequestParam ("cedula") int cedula,
			   @RequestParam ("nombre") String nombre,
			   @RequestParam ("foto") MultipartFile foto,
			   @RequestParam ("fecha") Date fecha,
			   @RequestParam ("cargo") int cargo) throws IOException {
		String urlImagen = empleadoService.actualizarImagen(foto);
	return empleadoService.ActualizarEmpleado(id,cedula,nombre,urlImagen,fecha,cargo);
	}
	
	@DeleteMapping("/eliminarEmpleado/{id}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable int id) {
		return ResponseEntity.ok().body(empleadoService.eliminarEmpleado(id));
	}
}
