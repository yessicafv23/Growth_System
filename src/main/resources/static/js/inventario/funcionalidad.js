const cerrar = document.getElementById('alerta-cerrar');
const alertas = document.getElementById('alertas');
const eliminar = document.getElementById('eliminar');
const entradasProd = document.querySelector('#formularioEntradas #entradasProd');
const formularioEntradas = document.getElementById('formularioEntradas');
const textAlerta = document.getElementById('formulario-textoAlerta');

if(cerrar != null){
   cerrar.addEventListener('click', () => {
      alertas.classList.add('d-none');
   });
}

formularioEntradas.addEventListener('submit', (e) => {
   e.preventDefault();
   if(entradasProd.value <= 0){
      e.preventDefault();
      textAlerta.classList.remove('d-none');
   }else{
      formularioEntradas.submit();
   }
})