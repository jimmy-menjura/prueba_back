/**
 * 
 */
 document.addEventListener("DOMContentLoaded", function(event) {
	obtenerListaSelect();
	const queryString = window.location.search;
	const param = new URLSearchParams(queryString);
	const id = param.get('id');
	if(id !== null && id !== undefined){
		botonActualizar();
		obtenerEmpleado(id)
	}
});

function mostrarImagen() {
  var preview = document.querySelector('img');
  var file    = document.querySelector('input[type=file]').files[0];
  var reader  = new FileReader();

  reader.onloadend = function () {
    preview.src = reader.result;
  }

  if (file) {
    reader.readAsDataURL(file);
  } else {
    preview.src = "";
  }
}

function botonActualizar(){
	let xhr = new XMLHttpRequest();
	const form = document.getElementById("form");
		document.getElementById("agregar").style = "display:none;"
		const button = document.createElement("button");
		button.className = "submitBtn";
		button.addEventListener('click',function(){
			const queryString = window.location.search;
	const param = new URLSearchParams(queryString);
	const id = param.get('id');
	let cedula	= document.getElementById("cedula").value; 
	let	nombre	= document.getElementById("nombre").value;
	let	foto	= document.getElementById("foto");
	let	fecha	= document.getElementById("fecha").value;
	let	cargo	= document.getElementById("cargo").value;
	const imagen = foto.files[0];
	const formData = new FormData();
	let fehaAformatear = new Date(fecha)
	let formato = formatDate(fehaAformatear);
	formData.append('cedula',parseInt(cedula))
	formData.append('nombre',nombre)
	formData.append('foto',imagen)
	formData.append('fecha',formato)
	formData.append('cargo',parseInt(cargo))
	xhr.open('PUT', `http://localhost:8081/api/actualizarEmpleado/${id}`);
	//xhr.setRequestHeader('Content-Type', 'multipart/form-data');
	xhr.onreadystatechange = function () {
			console.log("pasa aca 1")
			if (xhr.readyState === XMLHttpRequest.DONE) {
					console.log("pasa por aca")
				if (xhr.status === 200) {
					window.location.href = "index.html";
			}
		}
	}
	console.log("envia la data")
	xhr.send(formData);
		});
		button.innerHTML = "Actualizar"
		form.appendChild(button);
}
function obtenerEmpleado(id){
	const cedula	= document.getElementById("cedula");
	const nombre	= document.getElementById("nombre");
	const fecha	= document.getElementById("fecha");
	const cargo	= document.getElementById("cargo");
	const imageDisplay = document.getElementById('imageDisplay');
	let xhr = new XMLHttpRequest();
		xhr.open('GET', `http://localhost:8081/api/obtenerEmpleado/${id}`);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send();
		xhr.onload  = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					const arr = JSON.parse(xhr.responseText);
					let fehaAformatear = new Date(arr.fecha)
					let formato = formatDateJs(fehaAformatear);
					console.log("formato fecha" , formato)
					cedula.value = arr.cedula;
					nombre.value = arr.nombre;
					fecha.value = formato;
					cargo.value = arr?.cargo;
					
            		imageDisplay.src = arr.foto;
            		imageDisplay.style.display = 'block'; 
					
				}
			}
		}
}
function obtenerListaSelect(){
		let xhr = new XMLHttpRequest();
		xhr.open('GET', 'http://localhost:8081/api/obtenerCargo');
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send();
		xhr.onload  = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					const arr = JSON.parse(xhr.responseText);
					if(arr.length > 0){
						const select = document.getElementById("cargo");
						arr.map((d,i)=>{
							const option = document.createElement("option");
							option.value = arr[i].id
							option.text = arr[i].nombre
							select.appendChild(option)
						})
					}
				}
			}
	}	
}
function crear(){
	let cedula	= document.getElementById("cedula").value; 
	let	nombre	= document.getElementById("nombre").value;
	let	foto	= document.getElementById("foto");
	let	fecha	= document.getElementById("fecha").value;
	let	cargo	= document.getElementById("cargo").value;
	const imagen = foto.files[0];
	const formData = new FormData();
	let fehaAformatear = new Date(fecha)
	let formato = formatDate(fehaAformatear);
	formData.append('cedula',parseInt(cedula))
	formData.append('nombre',nombre)
	formData.append('foto',imagen)
	formData.append('fecha',formato)
	formData.append('cargo',parseInt(cargo))
	//jsonObj = {
	//	cedula:cedula,
	//	nombre:nombre,
	//	foto:imagen,
	//	fecha:fecha,
	//	cargo:cargo
	//}
	//let jsonData = JSON.stringify(jsonObj);
	//console.log("data:", jsonData)
	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8081/api/crearEmpleado');
	//xhr.setRequestHeader('Content-Type', 'multipart/form-data');
	xhr.onreadystatechange = function () {
			console.log("pasa aca 1")
			if (xhr.readyState === XMLHttpRequest.DONE) {
					console.log("pasa por aca")
				if (xhr.status === 200) {
					console.log("index.html")
					window.location.href = "index.html";
			}
		}
	}
	console.log("envia la data")
	xhr.send(formData);
}

function formatDate(date) {  
    const year = date.getFullYear();  
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Los meses son indexados desde 0  
    const day = String(date.getDate()).padStart(2, '0');  

    return `${year}/${month}/${day}`;  
}

function formatDateJs(date) {  
    const year = date.getFullYear();  
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Los meses son indexados desde 0  
    const day = String(date.getDate()).padStart(2, '0');  

    return `${year}-${month}-${day}`;  
}

function actualizar(){
	
}