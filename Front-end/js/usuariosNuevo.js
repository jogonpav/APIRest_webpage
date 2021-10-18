const url = "http://localhost:8080/ApiRest_InventaSoft/api/users"


const formUsuarios = document.querySelector('form')
let opcion = ''

opcion = document.getElementById('opcion').value
alert(opcion)

const ajax = (options) => {
    let { url, method, success, error, data } = options;
    const xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", (e) => {
        if (xhr.readyState !== 4) return;

        if (xhr.status >= 200 && xhr.status < 300) {
            let json = JSON.parse(xhr.responseText);
            success(json);
        } else {
            let message = xhr.statusText || "Ocurrió un error";
            error(`Error ${xhr.status}: ${message}`);
        }
    });

    xhr.open(method || "GET", url);
    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xhr.send(JSON.stringify(data));
};

 

formUsuarios.addEventListener('submit', (e) => {

const nombreUsuario= document.getElementById('nombres').value
 const emailUsuario = document.getElementById('correo').value
 const usuarioUsuario = document.getElementById('usuario').value
  const  passUsuario = document.getElementById('password').value
  const rolUsuario = document.getElementById('select-usuario').value

    e.preventDefault()
    let metodo = "POST"
    if (opcion.value == "editar") {
        metodo = "PUT"

    }
    ajax({
        url: url,
        method: metodo,
        headers: {
            'Content-Type': 'application/json'
        },
        success: (res) =>  window.location.href = "ListarUsuarios.html",
        error: (err) =>
            $form.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`),
        data: {
            "email": emailUsuario,
            "full_name": nombreUsuario,
            //"id": 122,
            "password": passUsuario,
            "profile_id": rolUsuario,
            "user": usuarioUsuario
        },
    });
   

    //modalClientes.hide()
})

 



