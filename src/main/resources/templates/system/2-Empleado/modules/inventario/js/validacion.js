const formulario = document.getElementById("formulario");
const input = document.querySelector("#formulario input");
const selects = document.querySelectorAll("#formulario select");

const expresion = {
    producto: /^\w[A-Za-z0-9\s\-\_]{6,40}$/,
    select: /^(?!none)([a-z0-9]+)$/,
};

const campos = {
    verificarproductos: false,
    verificartipos: false,
    verificarinventarios: false,
};

const validar = (e) => {
    switch (e.target.name) {
        case "productos":
            validacion(expresion.producto, e.target.value, e.target.name);
            break;
        case "tipos":
            validacion(expresion.select, e.target.value, e.target.name);
            break;
        case "inventarios":
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
            (campos.verificartipos === false &&
                    campos.verificarinventarios === false &&
                    campos.verificarproductos === false) ||
            (campos.verificartipos === false &&
                    campos.verificarinventarios === false &&
                    campos.verificarproductos === true) ||
            (campos.verificartipos === false &&
                    campos.verificarinventarios === true &&
                    campos.verificarproductos === true) ||
            (campos.verificartipos === true &&
                    campos.verificarinventarios === false &&
                    campos.verificarproductos === true) ||
            (campos.verificartipos === true &&
                    campos.verificarinventarios === true &&
                    campos.verificarproductos === false)
            ) {
        e.preventDefault();
        validarTodo("productos", campos.verificarproductos);
        validarTodo("tipos", campos.verificartipos);
        validarTodo("inventarios", campos.verificarinventarios);
    } else {
        formulario.submit();
    }
});
