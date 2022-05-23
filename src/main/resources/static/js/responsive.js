const boton = document.getElementById('sidebarCollapse');
const secciones = document.querySelectorAll('#secciones');

boton.addEventListener("click", () => {
    document.getElementById('contenidoHorizontal').classList.toggle('content-aumentar');
    document.getElementById('navegacion').classList.toggle('content__navbar-aumentar');
    document.getElementById('sidebar').classList.toggle('nav__sidebar-active');
});

const iconoSeccion = (icono) => {
    if (document.querySelector(`.${icono} i`).classList.contains("bi-plus-lg")) {
        document.querySelector(`.${icono} i`).classList.add("bi-dash-lg");
        document.querySelector(`.${icono} i`).classList.remove("bi-plus-lg");
    } else {
        document.querySelector(`.${icono} i`).classList.add("bi-plus-lg");
        document.querySelector(`.${icono} i`).classList.remove("bi-dash-lg");
    }
};

const seccionesCaso = (e) => {
    if (e.target.classList.contains("nav__seguimiento")) {
        iconoSeccion("nav__seguimiento");
    } else if (e.target.classList.contains("nav__inventario")) {
        iconoSeccion("nav__inventario");
    } else if (e.target.classList.contains("nav__salud")) {
        iconoSeccion("nav__salud");
    }
    ;
};

secciones.forEach((seccion) => {
    seccion.addEventListener("click", seccionesCaso);
});