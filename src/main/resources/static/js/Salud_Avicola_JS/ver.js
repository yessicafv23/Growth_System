
const ver = document.getElementById('ver');

ver.onclick = alerta;

function alerta() {
swal({
    title: "Maiz",
    text: "Categoria: Alimento para pollos, Imagen: ninguna",
    button: "Cerrar",
  });
}