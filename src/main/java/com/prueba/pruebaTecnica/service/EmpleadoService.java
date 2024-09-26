package com.prueba.pruebaTecnica.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.prueba.pruebaTecnica.model.Empleados;
import com.prueba.pruebaTecnica.repository.EmpleadosRepository;

@Service
public class EmpleadoService {
	@Autowired
	private EmpleadosRepository empleadosRepository;
	
	//@Value("${app.upload.dir}")
	private final String DIR_IMAGEN = "src/main/resources/static/images/";
	
	public Iterable<Empleados> ListadoEmpleados() {
		return  empleadosRepository.listadoEmpleados();
	}
	public Optional<Empleados> ListaEmpleado(int id) {
		return empleadosRepository.listaEmpleado(id);
	}

	public Empleados CrearEmpleado(int cedula,
									String nombre,
									String imagen,
									Date fecha,
									int cargo){
		return empleadosRepository.crearEmpleado(cedula,
												 nombre,
												 imagen,
												 fecha,
												 cargo);
	}

	public Empleados ActualizarEmpleado(int id,
			int cedula,
			String nombre,
			String foto,
			Date fecha,
			int cargo){
		Optional<Empleados> opt = empleadosRepository.obtenerEmpleadoPorId(id);
			Empleados gameRep = opt.get();
			gameRep.setCedula(cedula);
			gameRep.setNombre(nombre);
			gameRep.setFoto(foto);
			gameRep.setFecha(fecha);
			gameRep.setCargo(cargo);
			return empleadosRepository.actualizarEmpleado(gameRep);
		
	}

	public String eliminarEmpleado(int id){
		empleadosRepository.eliminarEmpleado(id);
		 return "eliminado";
	}
	public String guardarImagen(MultipartFile imagen) throws  IOException {
		
        File directory = new File(DIR_IMAGEN);  
        if (!directory.exists()) {  
            directory.mkdirs();  
        }  
        
        Path path = Paths.get(DIR_IMAGEN + imagen.getOriginalFilename());

        Files.createDirectories(path.getParent());

        Files.copy(imagen.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        
		return "/images/" + imagen.getOriginalFilename();
	}
	public String actualizarImagen(MultipartFile imagen) throws  IOException {
     
     Path path = Paths.get(DIR_IMAGEN + imagen.getOriginalFilename());

     Files.write(path, imagen.getBytes());
     
		return "images/" + imagen.getOriginalFilename();
	}
	
}
