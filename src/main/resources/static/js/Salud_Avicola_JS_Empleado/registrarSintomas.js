function registrarSintomas(){
 var fecha_sintoma, numero_galpon, descripcion_sintoma, cont;
 fecha_sintoma = document.getElementById("validationTooltip01 fecha_sintoma").value;
 numero_galpon = document.getElementById("validationTooltip02 numero_galpon").value;
 descripcion_sintoma = document.getElementById("validationTooltip03 descripcion_sintoma").value;
 cont = 0;
 

 if (fecha_sintoma ==="" ) {
  swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
  return false;
  cont=cont+1;
}
if (numero_galpon ==="" ) {
  swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
  return false;
  cont++;
}
if (descripcion_sintoma ==="" ) {
  swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
  return false;
  cont++;
}
if (cont===0) {
  swal({
    title: "Muy bien",
    text: "El registro se ha guardado satisfactoriamente!",
    type: "success",
    icon: 'success',
    timer: 4000,
    showConfirmButton: false
  });
  return false;
} else {


  alert(cont);
}
}



function eliminarSintomas(){
swal({
  title: "Estás seguro?",
  text: "Una vez que le des clic a ok, no podrás recuperar los registros!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((willDelete) => {
  if (willDelete) {
    swal("Tu registro se ha eliminado!", {
      icon: "success",
    });
  } else {
    swal("Has cancelado la eliminación del registro!");
  }
})
};