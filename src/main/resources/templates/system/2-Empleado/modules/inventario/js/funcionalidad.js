const botonAbrir = document.querySelectorAll("#abrirTabla");
const botonCerrar = document.getElementById("cerrarTabla");
const contenedor = document.getElementById("contenedor");

$(function () {
  $('[data-toggle="tooltip"]').tooltip()
})

const abrir = () => {
  document.getElementById("tabla").classList.toggle("tabla__data-active");
  document.getElementById("abrirTabla").classList.add("d-none");
  // document.getElementById("alerta").classList.add("d-none");
};

botonAbrir.forEach((boton) => {
  boton.addEventListener("click", abrir);
})

botonCerrar.addEventListener("click", () => {
  document.getElementById("tabla").classList.remove("tabla__data-active");
  setTimeout(() => {
    document.getElementById("abrirTabla").classList.remove("d-none");
  }, 500);
});

contenedor.addEventListener("click", () => {
  if (
    document.getElementById("tabla").classList.contains("tabla__data-active")
  ) {
    document.getElementById("tabla").classList.remove("tabla__data-active");
    setTimeout(() => {
      document.getElementById("abrirTabla").classList.remove("d-none");
    }, 500);
    return;
  }
});


