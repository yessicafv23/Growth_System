function registrarPrevenciones() {
    var id_prevencion, fecha_prevenciones, descripcion_solucion, descripcion_recomendacion, id_sintoma, cont;
    id_prevencion = document.getElementById("validationTooltipid_prevencion").value;
    fecha_prevenciones = document.getElementById("validationTooltip01fecha_prevenciones").value;
    descripcion_solucion = document.getElementById("validationTooltip02descripcion_solucion").value;
    descripcion_recomendacion = document.getElementById("validationTooltip03descripcion_recomendacion").value;
    id_sintoma = document.getElementById("validationTooltip04id_sintoma").value;
    cont = 0;


    if (id_prevencion === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont = cont + 1;
    }
    if (fecha_prevenciones === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    }
    if (descripcion_solucion === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    }
    if (descripcion_recomendacion === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    }
    if (id_sintoma === "") {
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



function eliminarPrevenciones() {
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