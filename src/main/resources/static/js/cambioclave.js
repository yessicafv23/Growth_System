const form = document.querySelector('form');
const inputclave = document.querySelector('#password');
const inputclave2 = document.querySelector("#password2");
const alerta = document.getElementById("alerta")
const alertaVacio = document.getElementById("alerta2")


form.addEventListener('submit', (e) => {
    if(inputclave.value == inputclave2.value && (inputclave.value != "" || inputclave2.value != "")){
        form.submit();
    }else if(inputclave.value != inputclave2.value){
        alerta.innerHTML = "Verifica que las claves se han iguales";
        alerta.classList.remove("d-none");
        setTimeout (() => {
            alerta.classList.add("d-none");
        }, 4000);
        e.preventDefault();
    } else if(inputclave.value == "" || inputclave2.value == ""){
        alertaVacio.innerHTML = "Complete los campos para verificar tu clave";
        alertaVacio.classList.remove("d-none");
        setTimeout (() => {
            alertaVacio.classList.add("d-none");
        }, 4000);
        e.preventDefault();
    }
    e.preventDefault();
});