function ingres(){
 var correo, contrasena, cont;
 correo =document.getElementById("correo").value;
 contrasena =document.getElementById("contrasena").value;
 terminos =document.getElementById("terminos").value;
 cont = 0;
 document.getElementById('bo').value = "asdasd";

        if((correo==="administrador@gmail.com" && contrasena==="12345") || (correo==="empleado@gmail.com" && contrasena==="12345")){
        //primer usuario
                if (correo === "administrador@gmail.com" && contrasena==="12345") {
                    window.location.href = ("../System/1- Administrador/index.jsp");
              
                }
                //segundo usuario
                if (correo === "empleado@gmail.com" && contrasena==="12345") {
                    window.location.href=("../System/2- Empleado/index.jsp");
                }
        }
        else if(correo==="" || contrasena===""){
            swal("No se aceptan campos vacios", "Vuelve a intentarlo", "error");
        }
        else{
            swal("Datos invalidos", "Vuelve a intentarlo", "error");  
        }
}
