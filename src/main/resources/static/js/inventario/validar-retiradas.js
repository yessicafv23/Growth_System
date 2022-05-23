localStorage.setItem('valor_entradas', document.getElementById('entradas').textContent)
const numeroSalidas = document.getElementById('salidas');
const numeroEntradasLocal = parseInt(localStorage.getItem('valor_entradas'));
const nombre_producto= document.getElementById('nombre_producto');
const formulario = document.querySelector('#formulario');

let validarCampo ={
    salidas: true
}

const validarSalidas = () => {
    numeroSalidas.classList.add('text-white');
    if(numeroSalidas.value <= numeroEntradasLocal && numeroSalidas.value > 0 && numeroSalidas.value != ''){
        numeroSalidas.classList.add('bg-success');
        numeroSalidas.classList.remove('bg-danger');
        numeroSalidas.placeholder = 'Correcto';
        validarCampo.salidas = true;
    }else{
        numeroSalidas.classList.add('bg-danger');
        numeroSalidas.classList.remove('bg-success');
        numeroSalidas.placeholder = 'Digite un cantidad mayor a 0';
        validarCampo.salidas = false;
    }
}

numeroSalidas.addEventListener('blur', validarSalidas)
numeroSalidas.addEventListener('change', validarSalidas)
numeroSalidas.addEventListener('keyup', validarSalidas)

formulario.addEventListener('submit', (e) => {
    e.preventDefault();
    if(validarCampo.salidas){
        Swal.fire({
            title: 'Desea retirar los productos',
            text: "Se van a retirar " + numeroSalidas.value + ' ' + nombre_producto.textContent,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si, retirar!',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                formulario.submit();
            }
            e.preventDefault();
        })
    }else{
        e.preventDefault();
    }
})