const correo = document.getElementById("correo1");
const alerta = document.getElementById("alerta");
const enviar = document.getElementById("enviar");

enviar.onclick = verificar;

function verificar() {
  if (correo.value === "") {
    alerta.classList.remove('d-none');
    setTimeout(() => {
      alerta.classList.add('d-none');
    }, 2500);
  } else {
    Swal.fire({
      icon: 'success',
      title: 'Restablecimiento en progreso, revisa tu correo',
      showConfirmButton: false
    });
    setTimeout(() => {
      location.href = "../../index.jsp";
    }, 2000);
  }
}
