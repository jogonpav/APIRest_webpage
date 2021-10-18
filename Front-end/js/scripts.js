const formulario=document.getElementById('formulario'); //Accederemos al formulario creado en HTML por medio de su atributo id
const inputs = document.querySelectorAll('#formulario input') //Creamos un array con los input que tiene el formulario




//Declaración de constantes Expresiones Regulares
const expresiones = {
	usuario: /^[a-zA-Z0-9\_\-]{4,16}$/, // Letras, numeros, guion y guion_bajo
	nombre: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
	password: /^.{8,30}$/, // 8 a 30 digitos. //Validar que tenga letras M m # caracter especial -- EXPRESIÓN A CAMBIAR PARA LA CONTRASEÑA
	correo: /^[a-zA-Z0-9._-]+@[a-zA-Z]+\.[a-zA-Z0-9-.]+$/,
	telefono: /^\d{7,10}$/, // 7 a 10 numeros. 
	referencia: /^[a-zA-Z0-9\_\#\s\-]{4,16}$/, // Letras, numeros, numeral guion y guion_bajo
	cantidad: /^\d{1,4}$/,
	codigo: /^[a-zA-Z0-9\_\#\-]{4,16}$/, // Letras, numeros, numeral guion y guion_bajo
	categoria: /^[a-zA-Z0-9À-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
	ubicacion: /^[a-zA-Z0-9\_\#\s\-]{4,40}$/, // Letras, numeros, guion y guion_bajo
}

// Crearemos cada campo con estado falso y al momento de que se haga la validación de cada formulario la idea es que lo cambie a true y de
// este modo valide todos los campos esten completamente llenos
const campos={
	usuario: false,
	nombre: false,
	password:false,
	correo:false,
	telefono:false,
	referencia:false,
	cantidad: false,
	codigo: false,
	categoria: false,
	ubicacion: false,
}

// Función con la que validaremos los formularios, con su name realizaremos la condición
//La idea es saber que formulario ha seleccionado el usuario, para de esta manera conocer como deben ser sus campos
const validarFormulario = (e)=>{

	// Sabemos en que formulario se esta trabajando y realizaremos codigos por cada uno de ellos
	switch(e.target.name){
		case "usuario":
			validar(expresiones.usuario, e.target, 'usuario');
		break;
		case "nombres":
			validar(expresiones.nombre, e.target, 'nombres');
			// console.log(e.target.name);
		break;
		case "password":
			validar(expresiones.password, e.target, 'password');
			validarContrasena();
			// console.log("pass");
		break;
		case "password2":
			validarContrasena();
			
			// validar(expresiones.password, e.target, 'password2');
			// console.log("pass2");
		break;
		case "correo":
			validar(expresiones.correo, e.target, 'correo');
			// console.log("correo");
		break;
		case "telefono":	
		validar(expresiones.telefono, e.target, 'telefono');
		break;
		case "referencia":	
		validar(expresiones.referencia, e.target, 'referencia');
		break;
		case "cantidad":	
		validar(expresiones.cantidad, e.target, 'cantidad');
		break;
		case "codigo":	
		validar(expresiones.codigo, e.target, 'codigo');
		break;
		case "categoria":	
		validar(expresiones.categoria, e.target, 'categoria');
		break;
		case "ubicacion":	
		validar(expresiones.ubicacion, e.target, 'ubicacion');
		break;
	}

}
// Función encargada de validar si esta bien escrito o no dentro del formulario
// expresiones.usuario = expresiones
// e.target=input
//grupo__usuario = grupo__campo (campo)
const validar = (expresiones,input,campo) =>{

	if(expresiones.test(input.value)){
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');

		document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-mensajeError`).classList.remove('formulario__mensaje-activo');
		campos[campo]=true;

	}else{
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-mensajeError`).classList.add('formulario__mensaje-activo');
		campos[campo]=false;
	}
}

// Funcion para validar que las contraseñas sean iguales
const validarContrasena = () =>{
	const contrasena1 = document.getElementById('password');
	const contrasena2 = document.getElementById('password2');

	if(contrasena1.value !== contrasena2.value){
		document.getElementById(`grupo__password2`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__password2`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__password2 i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__password2 i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__password2 .formulario__input-mensajeError`).classList.add('formulario__mensaje-activo');
		campos[password]=false;
	}else{
		document.getElementById(`grupo__password2`).classList.add('formulario__grupo-correcto');
		document.getElementById(`grupo__password2`).classList.remove('formulario__grupo-incorrecto');
		document.querySelector(`#grupo__password2 i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__password2 i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__password2 .formulario__input-mensajeError`).classList.remove('formulario__mensaje-activo');
		campos[password]=true;
	}
}

//SELECTS
function validar_select (){
	const es = document.getElementById('select-usuario')
	if (es.options[es.selectedIndex].text === "-- Seleccione una opción"){
		document.getElementById(`grupo__select-usuarios`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__select-usuarios`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__select-usuarios i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__select-usuarios i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__select-usuarios .formulario__input-mensajeError-select`).classList.add('formulario__mensaje-activo-select');
		campos[password]=false;
	}else{
		document.getElementById(`grupo__select-usuarios`).classList.add('formulario__grupo-correcto');
		document.getElementById(`grupo__select-usuarios`).classList.remove('formulario__grupo-incorrecto');
		document.querySelector(`#grupo__select-usuarios i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__select-usuarios i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__select-usuarios .formulario__input-mensajeError-select`).classList.remove('formulario__mensaje-activo-select');
		campos[password]=true;	
	}	
}

// INPUTS:
inputs.forEach((input) =>{
	input.addEventListener('keyup',validarFormulario);
	input.addEventListener('blur',validarFormulario);
})


// boton
//Funcion creada para que no nos haga envío del formulario, esto con el fin de solo realizar la validación
formulario.addEventListener('submit',(e)=>{
	e.preventDefault();  
	const terminos = document.getElementById('terminosC');
	if(campos.usuario && campos.nombres && campos.password && campos.correo && campos.telefono && terminos.checked){
		formulario.reset();
		document.getElementById('formulario__mensaje-exit').classList.add('formulario__mensaje-exit-activo');
		// Funcion para que se quite el mensaje de enviado con exito luego de unos segundos
		setTimeout(()=>{
			document.getElementById('formulario__mensaje-exit').classList.remove('formulario__mensaje-exit-activo');
		},5000);
	}
	// Funcion preventDefault para que no nos permita el envio 
});


// Nota:
// Verificar 
// cantidad de numeros que se pueden telefono en el correo, ya que solo trabajaremos en colombia
// Contidad de caracteres para la contraseña
// cantidad maxima para el nombre
// validar el tamaño maximo del archivo -- pendiente de inicio

//
