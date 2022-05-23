// document.addEventListener('DOMContentLoaded', function () {
const correo = document.getElementById("correo");
const clave = document.getElementById("contrasena");
const btn = document.getElementById("buttonIngresar");
const mensaje = document.getElementById("alerta");
// Variables para mostrar clave
const ojo = document.getElementById("ojo");
let ver = document.getElementById("contrasena");
const icon = document.getElementById("icon_clave");

// Ingresar
btn.onclick = ingresar;
// Mostrar
ojo.addEventListener("click", mostrar);

// Funcion ingresar
function ingresar() {
    if (
            (correo.value === "administrador@gmail.com" && clave.value === "12345") ||
            (correo.value === "empleado@gmail.com" && clave.value === "12345")
            ) {
        //Administrador
        if (correo.value === "administrador@gmail.com" && clave.value === "12345") {
            window.location.href = "/Admin";
            //Empleado
        } else if (
                correo.value === "empleado@gmail.com" &&
                clave.value === "12345"
                ) {
        	window.location.href = "/Empleado";
        }
    } else {
        mensaje.classList.remove("d-none");
        setTimeout(function () {
            mensaje.classList.add("d-none");
        }, 3500);
    }
}

// Funcion clave
function mostrar() {
    if (ver.type === "password") {
        ver.type = "text";
        icon.classList.remove("fa-eye");
        icon.classList.add("fa-eye-slash");
    } else {
        ver.type = "password";
        icon.classList.remove("fa-eye-slash");
        icon.classList.add("fa-eye");
    }
}
// });
