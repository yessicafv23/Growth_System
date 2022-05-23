const ojo = document.getElementById("ojo");
let inputClave = document.getElementById("password");

ojo.addEventListener("click", (e) => {
    if (inputClave.type == "password"){
        inputClave.type = "text";
    }else {
        inputClave.type = "password";
    }
})