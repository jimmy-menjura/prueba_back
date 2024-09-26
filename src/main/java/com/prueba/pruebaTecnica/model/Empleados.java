package com.prueba.pruebaTecnica.model;

import java.io.File;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empleados {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
public Integer Cedula;
private String Nombre;
public String Foto;
private Date Fecha;
//@ManyToOne(fetch = FetchType.LAZY)
public Integer Cargo;

public Empleados() {}
public Empleados(int cedula,
				String nombre,
				String imagen,
				Date fecha,
				int cargo) {
	this.Cedula = cedula;
	this.Nombre = nombre;
	this.Foto = imagen;
	this.Fecha = fecha;
	this.Cargo = cargo;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getCedula() {
	return Cedula;
}
public void setCedula(Integer cedula) {
	Cedula = cedula;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getFoto() {
	return Foto;
}
public void setFoto(String foto) {
	Foto = foto;
}
public Date getFecha() {
	return Fecha;
}
public void setFecha(Date fecha) {
	Fecha = fecha;
}
public Integer getCargo() {
	return Cargo;
}
public void setCargo(Integer cargo) {
	Cargo = cargo;
}




}
