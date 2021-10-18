const url = "http://localhost:8080/ApiRest_InventaSoft/api/login"


const formLogin = document.querySelector('form')
const usuarioLogin= document.getElementById('user')
const passwordLogin = document.getElementById('password')



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


formLogin.addEventListener('submit', (e) => {

    e.preventDefault()
     
    
    ajax({
        url: url + "/" + usuarioLogin.value,
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        },
        success: (res) => {
            
            if(res.user == usuarioLogin.value && res.password == passwordLogin.value){
                if(res.profile_id==2){
                    window.location.href="MenuUsuario.html";
                }else if(res.profile_id==3){
                   window.location.href="MenuAlmacen.html";
                }else{
                    window.location.href="MenuAdministrador.html";
                }
                
            }else{
               
               passwordLogin.value=''
               usuarioLogin.value=''
               alertify.error('Credenciales Incorrectas')
            }

        },
        error: (err) => {
            console.log(err);
            
        },
    
    });

});
    


 