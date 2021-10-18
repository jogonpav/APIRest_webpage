const url = "http://localhost:8080/ApiRest_InventaSoft/api/products"
const contenedor = document.querySelector('tbody')
let resultados = ''

const modalProductos = new bootstrap.Modal(document.getElementById('modalProducto'))
const formProductos= document.querySelector('form')
const codigoProducto= document.getElementById('codigo')
const nombreProducto = document.getElementById('nombres')
const referenciaProducto = document.getElementById('referencia')
const categoriaProducto = document.getElementById('categoria')
const fechaExpiracionProducto = document.getElementById('fechaExpiracion')
const ubicacionProducto = document.getElementById('ubicacion')
const idProducto = document.getElementById('id')

var opcion = ''

btnCrear.addEventListener('click', () => {
    codigoProducto.value = ''
    nombreProducto.value = ''
    referenciaProducto.value = ''
    categoriaProducto.value = ''
    fechaExpiracionProducto.value = ''
    ubicacionProducto.value = ''
   //idCliente.disabled = false
    modalProductos.show()
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

            res.forEach((productos) => {
                resultados += `<tr >
                        <td width="5%">${productos.id}</td>
                        <td width="15%">${productos.code}</td>
                        <td width="15%">${productos.product_name}</td>
                        <td width="15%">${productos.reference}</td>
                        <td width="15%">${productos.category}</td>
                        <td width="15%">${productos.quantity}</td>
                        <td width="15%">${productos.location}</td>
                        
                        <td class="text-center" width="25%">
                            <a class='btn btnEditar btn-info btn-xs' ><span class="glyphicon glyphicon-floppy-saved"></span> Editar</a>
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
        idProducto.value = fila.children[0].innerHTML
        codigoProducto.value = fila.children[1].innerHTML
        nombreProducto.value = fila.children[2].innerHTML
        referenciaProducto.value = fila.children[3].innerHTML
        categoriaProducto.value = fila.children[4].innerHTML
        ubicacionProducto.value = fila.children[6].innerHTML
        fechaExpiracionProducto.value = fila.children[6].innerHTML
        //idCliente.disabled = true
        opcion = 'editar'
        modalProductos.show()
    }
    
});

formProductos.addEventListener('submit', (e) => {

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
            "category_id": categoriaProducto.value,
            "code": codigoProducto.value,
            "expiration": fechaExpiracion.value,
            "id": idProducto.value,
            "location": ubicacionProducto.value,
            "product_name": nombreProducto.value,
            "reference": referenciaProducto.value
        },
    });
        modalProductos.hide()
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
            "category_id": categoriaProducto.value,
            "code": codigoProducto.value,
            "expiration": fechaExpiracion.value,
            //"id": 1,
            "location": ubicacionProducto.value,
            "product_name": nombreProducto.value,
            "reference": referenciaProducto.value
    
        },
    });
    modalProductos.hide()
}
    
})

 



