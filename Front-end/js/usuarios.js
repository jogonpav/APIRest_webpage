const url = "http://localhost:8080/ApiRest_InventaSoft/api/users"
const contenedor = document.querySelector('tbody')
let resultados = ''


const modalUsuarios = new bootstrap.Modal(document.getElementById('modalUsuario'))
const formUsuarios = document.querySelector('form')
const nombreUsuario= document.getElementById('nombres')
const emailUsuario = document.getElementById('correo')
const usuarioUsuario = document.getElementById('usuario')
const passUsuario = document.getElementById('password')
const rolUsuario = document.getElementById('select-usuario')
const idUsuario = document.getElementById('id')

var opcion = ''

btnCrear.addEventListener('click', () => {
    nombreUsuario.value = ''
    emailUsuario.value = ''
    usuarioUsuario.value = ''
    passUsuario.value = ''
    rolUsuario.value = ''
   //idCliente.disabled = false
    modalUsuarios.show()
    opcion = 'crear'
})



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

const getAll = () => {
    ajax({
        url: url,
        method: "GET",
        success: (res) => {
            console.log(res);

            res.forEach((usuarios) => {
                resultados += `<tr >
                        <td width="15%">${usuarios.id}</td>
                        <td width="15%">${usuarios.full_name}</td>
                        <td width="15%">${usuarios.email}</td>
                        <td width="15%">${usuarios.user}</td>
                        <td width="15%">${usuarios.profile}</td>
                        
                        <td class="text-center" width="15%">
                            <a class='btn btnEditar btn-info btn-xs' " ><span class="glyphicon glyphicon-floppy-saved"></span> Editar</a>
                            <a class='btn btnBorrar btn-danger btn-xs' ><span class="glyphicon glyphicon-floppy-saved"></span> Eliminar</a>
                        </td>
                    </tr>`
            });

            contenedor.innerHTML = resultados

        },
        error: (err) => {
            console.log(err);
            $table.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`);
        },
    });
};

document.addEventListener("DOMContentLoaded", getAll);

document.addEventListener("click", (e) => {

    if (e.target.matches(".btnBorrar")) {
        const fila = e.target.parentNode.parentNode
        const id = fila.firstElementChild.innerHTML
        
        alertify.confirm(`¿Estás seguro de eliminar el id ${id}?`,
           
            function () {
                ajax({
                    url: url + "/" + id,
                    
                    method: "DELETE",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    success: (res) => location.reload(),
                    error: (err) => alert(err),
                });
                alertify.success('Registro eliminado')
            },
            function () {
                alertify.error('Cancel')
            });



    }
    if (e.target.matches(".btnEditar")) {
        const fila = e.target.parentNode.parentNode
        idUsuario.value = fila.children[0].innerHTML
        nombreUsuario.value = fila.children[1].innerHTML
        emailUsuario.value = fila.children[2].innerHTML
        usuarioUsuario.value = fila.children[3].innerHTML
        passUsuario.value = fila.children[4].innerHTML
        rolUsuario.value = fila.children[5].innerHTML
        //idCliente.disabled = true
        opcion = 'editar'
        modalUsuarios.show()
    }
    
});

formUsuarios.addEventListener('submit', (e) => {

    e.preventDefault()
    let metodo = ""
    if (opcion == 'editar') {
        metodo = "PUT"
        ajax({
        url: url,
        method: metodo,
        headers: {
            'Content-Type': 'application/json'
        },
        success: (res) => location.reload(),
        error: (err) =>
            $form.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`),
        data: {
            "email": emailUsuario.value,
            "full_name": nombreUsuario.value,
            "id": idUsuario.value,
            "password": passUsuario.value,
            "profile_id": rolUsuario.value,
            "user": usuarioUsuario.value
        },
    });
        modalUsuarios.hide()
    }
    if(opcion == 'crear'){
    ajax({
        url: url,
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        success: (res) => location.reload(),
        error: (err) =>
            $form.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`),
        data: {
            "email": emailUsuario.value,
            "full_name": nombreUsuario.value,
            "password": passUsuario.value,
            "profile_id": rolUsuario.value,
            "user": usuarioUsuario.value
        },
    });
    modalUsuarios.hide()
}
    
})

 



