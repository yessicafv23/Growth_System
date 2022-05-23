const inputs = document.querySelectorAll('form input');
const selects = document.querySelectorAll('form select');
const formulario = document.getElementById('formulario');

const expresiones = {
    IDUsuario: /^\w[0-9]{4,15}$/,
    telefono: /^\w[0-9]{5,15}$/,
    nombre: /^\w[A-z,\ ,\á,\é,\í,\ó,\ú]{1,45}$/,
    apellido: /^\w[A-z,\ ,\á,\é,\í,\ó,\ú]{1,45}$/,
    username: /[a-z0-9!#$%&'*+\/\=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+\/\=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/,
}

const verificaciones = {
    IDUsuario: false,
    nombre: false,
    apellido: false,
    username: false,
    password: false,
    telefono: false,
    tipo_documento: false,
    ciudad: false,
    cargo: false
}

const validarCampo = (e) => {
    let contenido = e.target.value;
    switch (e.target.id){
        case "IDUsuario":
            formulario.IDUsuario.value = contenido
                .replace(/\s/g, "")
                .replace(/\D/g, "");
            validacion(expresiones.IDUsuario, e.target.id, contenido);
            break;
        case "nombre":
            formulario.nombre.value = contenido
                .replace(/\d/g, "")
                .replace(/[\.\,\"\?\!\;\:\#\$\%\&\(\)\*\+\-\/\<\>\=\@\[\]\\\^\_\{\}\|\~\`\']/g, "");
            validacion(expresiones.nombre, e.target.id, contenido);
            break;
        case "apellido":
            formulario.apellido.value = contenido
                .replace(/\d/g, "")
                .replace(/[\.\,\"\?\!\;\:\#\$\%\&\(\)\*\+\-\/\<\>\=\@\[\]\\\^\_\{\}\|\~\`\']/g, "");
            validacion(expresiones.apellido, e.target.id, contenido);
            break;
        case "username":
            validacion(expresiones.username, e.target.id, contenido);
            break;
        case "password":
            if (e.target.value == ""){
                verificaciones['password'] = false;
            }else{
                verificaciones['password'] = true;
            }
            break;
        case "telefono":
            formulario.telefono.value = contenido
                .replace(/\s/g, " ")
                .replace(/\D/g, "");
            validacion(expresiones.telefono, e.target.id, contenido);
            break;
        case "tipo_documento":
            if (e.target.value == "none" || e.target.value >= 3){
                verificaciones['tipo_documento'] = false;
            }else{
                verificaciones['tipo_documento'] = true;
            }
            break;
        case "ciudad":
            if (e.target.value == "none" || e.target.value >= 3){
                verificaciones['ciudad'] = false;
            }else{
                verificaciones['ciudad'] = true;
            }
            break;
        case "cargo":
            if (e.target.value == "none" || e.target.value >= 3){
                verificaciones['cargo'] = false;
            }else{
                verificaciones['cargo'] = true;
            }
            break;
        default:
            console.log("error");
    }
}

inputs.forEach((input) => {
    input.addEventListener('keyup', validarCampo);
    input.addEventListener('blur', validarCampo);
    input.addEventListener('change', validarCampo);
})

selects.forEach((select) => {
    select.addEventListener('blur', validarCampo);
    select.addEventListener('click', validarCampo);
    select.addEventListener('keyup', validarCampo);
})

const validacion = (expresion, id, contenido) => {
    document.getElementById(`${id}`).classList.add('text-white');
    if (expresion.test(contenido)){
        document.getElementById(`${id}`).classList.add('bg-success');
        document.getElementById(`${id}`).classList.remove('bg-danger');
        verificaciones[id] = true;
    }else{
        document.getElementById(`${id}`).classList.add('bg-danger');
        document.getElementById(`${id}`).classList.remove('bg-success');
        verificaciones[id] = false;
    }
}

formulario.addEventListener('submit', (e) => {
    e.preventDefault()
    if(verificaciones.IDUsuario && verificaciones.nombre && verificaciones.apellido &&
    verificaciones.password && verificaciones.telefono && verificaciones.tipo_documento &&
    verificaciones.ciudad && verificaciones.cargo){
        formulario.submit();
        console.log('Se envio');
    }else{
        e.preventDefault();
        console.log('verifique los campos');
    }
})