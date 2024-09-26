/**
 * 
 */

 document.addEventListener("DOMContentLoaded", function(event) {
	mostrarEmpleados();
});

	function mostrarEmpleados(){
		var xhr = new XMLHttpRequest();
		xhr.open('GET', 'http://localhost:8081/api/obtenerEmpleados');
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send();
		xhr.onload  = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					const arr = JSON.parse(xhr.responseText);
					console.log("datos arr" , arr)
					if(arr.length > 0){
						const data = document.getElementById("data");
						console.log("datos data", data)
						 arr.map((datos)=>{
							const tr = document.createElement("tr");
							const td = document.createElement("td");
							td.innerHTML =  datos.cedula
							tr.appendChild(td);
							const td1 = document.createElement("td");
							td1.innerHTML =  datos.nombre
							tr.appendChild(td1);
							const td2 = document.createElement("td");
							tr.appendChild(td2);
							const img = document.createElement("img");
							img.style = "width:250px;heigth:250px;"
							img.src = 'http://localhost:8081/'+datos.foto.replace(/\\/g, '/');
							td2.appendChild(img)
							const td3 = document.createElement("td");
							td3.innerHTML =  datos.fecha
							tr.appendChild(td3);
							const td4 = document.createElement("td");
							td4.innerHTML =  datos.cargo
							tr.appendChild(td4);
							const buttonUppdate = document.createElement("button");
							buttonUppdate.onclick = function(){
								window.location.href = `crear.html?id=${datos.id}`
							} 
							buttonUppdate.innerHTML =  "Actualizar"
							tr.appendChild(buttonUppdate);
							const buttonDelete = document.createElement("button");
							buttonDelete.onclick = function(){
								var xhr = new XMLHttpRequest();
								xhr.open('DELETE', `http://localhost:8081/api/eliminarEmpleado/${datos.id}`);
								xhr.setRequestHeader('Content-Type', 'application/json');
								xhr.send();
								xhr.onload  = function() {
									if (xhr.readyState === XMLHttpRequest.DONE) {
										if (xhr.status === 200) {
										window.location.reload();
										}
									}
								}
							} 
							buttonDelete.innerHTML =  "Eliminar"
							tr.appendChild(buttonDelete);
							data.appendChild(tr);
						})
					}
				}
			}
		}
	}
					