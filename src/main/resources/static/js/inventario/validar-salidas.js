const campos = document.querySelectorAll("#campos")
const campo_entrada = document.getElementById("entradasProd");
const campo_salida = document.getElementById("salidasProd");
const formulario = document.getElementById("formulario");
let valor_entrada = parseInt(campo_entrada.value);
let valor_salida = parseInt(campo_salida.value);

const validacion = () => {
    valor_entrada = parseInt(campo_entrada.value);
    valor_salida = parseInt(campo_salida.value);
}

campos.forEach((campo) => {
    campo.addEventListener('keyup', validacion);
    campo.addEventListener('change', validacion);
    campo.addEventListener('blur', validacion)
})

window.setInterval(validacion, 100);

formulario.addEventListener('submit', (e) => {
    e.preventDefault()
    if (valor_salida <= valor_entrada) {
        formulario.submit();
    } else {
        e.preventDefault();
        const error = document.getElementById("error_salidas");
        error.classList.remove("d-none")
        setTimeout(() => {
            error.classList.add("d-none");
        }, 3500)

    }
})


