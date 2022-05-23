const formulario = document.getElementById("formulario");
const input = document.querySelector("#formulario #nombreProd");
const selects = document.querySelectorAll("#formulario select");

const expresion = {
    nombreProd: /^\w[A-Za-z0-9\s\-_]{6,40}$/,
    select: /^(?!none)([a-z0-9]+)$/,
};

const campos = {
    verificarnombreProd: false,
    verificarpIdtipo: false,
    verificarpIdinventario: false,
};

const validar = (e) => {
    switch (e.target.name) {
        case "nombreProd":
            validacion(expresion.nombreProd, e.target.value, e.target.name);
            break;
        case "pIdtipo":
            validacion(expresion.select, e.target.value, e.target.name);
            break;
        case "pIdinventario":
            validacion(expresion.select, e.target.value, e.target.name);
            break;
    }
};

const validacion = (expresiones, valor, propiedad) => {
    const Propiedad = `${propiedad}`.replace(/\b\w/g, (l) => l.toUpperCase());
    if (expresiones.test(valor)) {
        document
            .getElementById(`icono${Propiedad}`)
            .classList.add("bi-check-circle-fill");
        document
            .getElementById(`icono${Propiedad}`)
            .classList.add("form__iconos-validado");
        document
            .getElementById(`${propiedad}`)
            .classList.remove("form__grupo-invalido");
        document
            .getElementById(`icono${Propiedad}`)
            .classList.remove("form__iconos-invalidado");
        document
            .getElementById(`icono${Propiedad}`)
            .classList.remove("bi-dash-circle-fill");
        campos[`verificar${propiedad}`] = true;
    } else {
        document
            .getElementById(`${propiedad}`)
            .classList.add("form__grupo-invalido");
        document
            .getElementById(`icono${Propiedad}`)
            .classList.add("form__iconos-invalidado");
        document
            .getElementById(`icono${Propiedad}`)
            .classList.add("bi-dash-circle-fill");
        document
            .getElementById(`icono${Propiedad}`)
            .classList.remove("bi-check-circle-fill");
        campos[`verificar${propiedad}`] = false;
    }
};

input.addEventListener("keyup", validar);
input.addEventListener("blur", validar);
selects.forEach((select) => {
    select.addEventListener("change", validar);
    select.addEventListener("change", validar);
});

//----------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------

const validarTodo = (campo, validacion) => {
    const Campo = `${campo}`.replace(/\b\w/g, (l) => l.toUpperCase());
    if (!validacion) {
        document.getElementById(`${campo}`).classList.add("form__grupo-invalido");
        document
            .getElementById(`icono${Campo}`)
            .classList.add(`form__iconos-invalidado`);
        document
            .getElementById(`icono${Campo}`)
            .classList.add(`bi-dash-circle-fill`);
        document
            .getElementById(`icono${Campo}`)
            .classList.remove(`bi-check-circle-fill`);
    } else {
        document
            .getElementById(`icono${Campo}`)
            .classList.remove(`form__iconos-invalidado`);
        document
            .getElementById(`icono${Campo}`)
            .classList.add(`form__iconos-validado`);
        document
            .getElementById(`icono${Campo}`)
            .classList.remove(`bi-dash-circle-fill`);
        document
            .getElementById(`icono${Campo}`)
            .classList.add(`bi-check-circle-fill`);
    }
};

formulario.addEventListener("submit", (e) => {
    if (
        (campos.verificarpIdtipo === false &&
            campos.verificarpIdinventario === false &&
            campos.verificarnombreProd === false) ||
        (campos.verificarpIdtipo === false &&
            campos.verificarpIdinventario === false &&
            campos.verificarnombreProd === true) ||
        (campos.verificarpIdtipo === false &&
            campos.verificarpIdinventario === true &&
            campos.verificarnombreProd === true) ||
        (campos.verificarpIdtipo === true &&
            campos.verificarpIdinventario === false &&
            campos.verificarnombreProd === true) ||
        (campos.verificarpIdtipo === true &&
            campos.verificarpIdinventario === true &&
            campos.verificarnombreProd === false)
    ) {
        e.preventDefault();
        validarTodo("nombreProd", campos.verificarnombreProd);
        validarTodo("pIdtipo", campos.verificarpIdtipo);
        validarTodo("pIdinventario", campos.verificarpIdinventario);
    } else {
        formulario.submit();
    }
});
