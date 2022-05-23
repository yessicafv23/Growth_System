function guardarCambios() {
    swal("Muy bien", "Se han guardado correctamente los cambios realizados", "success");
}

/*  Registrar Gastos  */

function gastos() {
    var fechaRegistro, tipoGasto, importe, cont;
    fechaRegistro = document.getElementById("fechaRegistro").value;
    tipoGasto = document.getElementById("tipoGasto").value;
    importe = document.getElementById("importe").value;
    cont = 0;

    if (fechaRegistro === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    }

    if (tipoGasto === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    }
    if (importe === "") {
        swal("FORMULARIO INCOMPLETO", "Vuelve a intentarlo", "error");
        return false;
        cont++;
    }

    if (cont === 0) {
        swal({
            title: "Muy bien",
            text: "El gasto se ha registrado Correctamente",
            type: "success",
            timer: 4000,
            showConfirmButton: false
        });
        return false;
    } else {


        alert(cont);
    }
}

function eliminarRegistro() {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning', showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                    )
        }
    })
}

$("#btnEliminar").click(function(){
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning', 
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                    )
        }
    })
});
