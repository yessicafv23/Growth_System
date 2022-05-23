function consultarTratamientos(){
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