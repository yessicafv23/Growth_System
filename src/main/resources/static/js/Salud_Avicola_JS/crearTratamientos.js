function crearTratamientos() {
    var id_tratamiento, nombre_tratamiento, tipo_tratamiento, descripcion_tratamiento, id_enfermedad, cont;
    id_tratamiento = document.getElementById("id_tratamiento").value;
    nombre_tratamiento = document.getElementById("nombre_tratamiento").value;
    tipo_tratamiento = document.getElementById("vtipo_tratamiento").value;
    descripcion_tratamiento = document.getElementById("descripcion_tratamiento").value;
    id_enfermedad = document.getElementById("id_enfermedad").value;
    cont = 0;


    if (id_tratamiento === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont = cont + 1;
    }
    if (nombre_tratamiento === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    }
    if (tipo_tratamiento === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    }
   if (descripcion_tratamiento === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    } 
    if (id_enfermedad === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    } 
    if (cont === 0) {
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


function eliminarTratamientos() {
    swal({
        title: "Estás seguro?",
        text: "Una vez que le des clic a ok, no podrás recuperar los registros!",
        icon: "warning",
        buttons: true,
        dangerMode: true
    })
    .then((willDelete) => {
        if (willDelete) {
            swal("Tu registro se ha eliminado!", {
                icon: "success"
            });
        } else {
            swal("Has cancelado la eliminación del registro!");
        }
    });
}