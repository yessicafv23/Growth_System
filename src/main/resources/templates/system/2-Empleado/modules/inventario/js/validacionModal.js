const formularioTipo = document.getElementById("formularioTipo");
const formularioInventario = document.getElementById("formularioInventario");
const inputTipos = document.querySelectorAll("#formularioTipo input");
const inputInventarios = document.querySelectorAll(
  "#formularioInventario input"
);
const expresionvalidar = /^\w[A-Za-z0-9\s\-\_]{6,40}$/;

const validarModal = (e) => {
  switch (e.target.name) {
    case "modalformTipo":
      validarModalesAll(e.target.name, e.target.value, "tipo");
      break;
    case "modalformInventario":
      validarModalesAll(e.target.name, e.target.value, "inventario");
      break;
  }
};

const validarModalesAll = (targetName, valor, nombre) => {
  nombre = `${nombre}`.replace(/\b\w/g, (l) => l.toUpperCase());
  if (expresionvalidar.test(valor)) {
    document
      .getElementById(`${targetName}`)
      .classList.remove("modal__input-invalidado");
    document
      .getElementById(`modalformIcono${nombre}`)
      .classList.add("modal__icono-validado");
    document
      .getElementById(`modalformIcono${nombre}`)
      .classList.remove("bi-dash-circle-fill");
    document
      .getElementById(`modalformIcono${nombre}`)
      .classList.add("bi-check-circle-fill");
  } else {
    document
      .getElementById(`${targetName}`)
      .classList.add("modal__input-invalidado");
    document
      .getElementById(`modalformIcono${nombre}`)
      .classList.add("modal__icono-invalidado");
    document
      .getElementById(`modalformIcono${nombre}`)
      .classList.add("bi-dash-circle-fill");
  }
};

inputTipos.forEach((input) => {
  input.addEventListener("keyup", validarModal);
  input.addEventListener("blur", validarModal);
});

inputInventarios.forEach((input) => {
  input.addEventListener("keyup", validarModal);
  input.addEventListener("blur", validarModal);
});

formularioTipo.addEventListener("submit", (e) => {
  if (expresionvalidar.test(document.getElementById("modalformTipo").value)) {
    window.alert("Enviado");
  } else {
    e.preventDefault();
    document
      .getElementById(`modalformTipo`)
      .classList.add("modal__input-invalidado");
    document
      .getElementById(`modalformIconoTipo`)
      .classList.add("modal__icono-invalidado");
    document
      .getElementById(`modalformIconoTipo`)
      .classList.add("bi-dash-circle-fill");
  }
});

formularioInventario.addEventListener("submit", (e) => {
  if (
    expresionvalidar.test(document.getElementById("modalformInventario").value)
  ) {
    window.alert("Enviado");
  } else {
    e.preventDefault();
    document
      .getElementById(`modalformInventario`)
      .classList.add("modal__input-invalidado");
    document
      .getElementById(`modalformIconoInventario`)
      .classList.add("modal__icono-invalidado");
    document
      .getElementById(`modalformIconoInventario`)
      .classList.add("bi-dash-circle-fill");
  }
});
