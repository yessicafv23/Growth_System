function gestionarEnfermedades(){
 var fecha_enfermedad, tipo_enfermedad, descripcion_enfermedad, cont;
 id_enfermedad = document.getElementById("validationTooltipid_enfermedad").value;
 nombre_enfermedad = document.getElementById("validationTooltip01nombre_enfermedad").value;
 tipo_enfermedad = document.getElementById("validationTooltip02tipo_enfermedad").value;
 descripcion_enfermedad = document.getElementById("validationTooltip03descripcion_enfermedad").value;
 cont = 0;
 

 if (id_enfermedad ==="" ) {
  swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
  return false;
  cont=cont+1;
}
if (fecha_enfermedad ==="" ) {
  swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
  return false;
  cont++;
}
if (tipo_enfermedad ==="" ) {
  swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
  return false;
  cont++;
}
if (descripcion_enfermedad ==="" ) {
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




function eliminarEnfermedades(){
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