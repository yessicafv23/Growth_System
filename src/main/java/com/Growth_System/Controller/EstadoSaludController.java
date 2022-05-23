package com.Growth_System.Controller;

import java.util.List;

import com.Growth_System.Service.UsuarioPersonalizadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Growth_System.Entity.Estado_salud;
import com.Growth_System.Entity.Galpon;
import com.Growth_System.Entity.Tipo_estadosalud;
import com.Growth_System.Service.EstadoSaludService;
import com.Growth_System.Service.GalponService;
import com.Growth_System.Service.TipoEstadoSaludService;

@Controller
public class EstadoSaludController {

    @Autowired
    private EstadoSaludService estadoSaludService;

    @Autowired
    private GalponService galponService;

    @Autowired
    private TipoEstadoSaludService tipoEstadoSaludService;

    @Autowired
    private UsuarioPersonalizadoService usuarioPersonalizadoService;

    @GetMapping("/asignarEstadoSalud")
    public String asignarEstadoSalud(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Estado_salud estado_salud = new Estado_salud();

        List<Galpon> listaGalpones = galponService.listaGalpon();
        List<Tipo_estadosalud> listaEstados = tipoEstadoSaludService.listaTipoSalud();

        model.addAttribute("estado_salud", estado_salud);
        model.addAttribute("galpones", listaGalpones);
        model.addAttribute("tiposEstados", listaEstados);
        return "system/2-Empleado/modules/SeguimientoAvicola/asignar_estado";
    }

    @GetMapping("/consultarEstadoSalud")
    public String consultarEstadoSalud(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        List<Estado_salud> listadoEstadosSalud = estadoSaludService.listarTodos();
        model.addAttribute("estadosSalud", listadoEstadosSalud);
        return "system/1-Administrador/modules/SeguimientoAvicola/consultarEstadoSalud";
    }

    @PostMapping("/guardarEstadoSalud")
    public String asignarEstado(@ModelAttribute Estado_salud estado_salud, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        estadoSaludService.guardarEstado(estado_salud);
        System.out.println("Estado asignado correctamente");
        attribute.addFlashAttribute("success", "Estado de Salud Guardado con Exito!");
        return "redirect:/asignarEstadoSalud";
    }

    @PostMapping("/actualizarEstado")
    public String actualizarEstado(@ModelAttribute Estado_salud estado_salud, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        estadoSaludService.guardarEstado(estado_salud);
        System.out.println("Estado de salud actualizado con exito");
        attribute.addFlashAttribute("success", "El estado de salud se ha actualizado correctamente.");
        return "redirect:/consultarEstadoSalud";
    }

    //Editar estado de salud
    @GetMapping("/editarEstadoSalud/{idEstadoSalud}")
    public String editarEstadoSalud(@PathVariable("idEstadoSalud") Long idEstado, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Estado_salud estado_salud = estadoSaludService.buscarPorId(idEstado);

        List<Galpon> listaGalpones = galponService.listaGalpon();
        List<Tipo_estadosalud> listaEstados = tipoEstadoSaludService.listaTipoSalud();

        model.addAttribute("estado_salud", estado_salud);
        model.addAttribute("galpones", listaGalpones);
        model.addAttribute("tiposEstados", listaEstados);
        return "system/1-Administrador/modules/SeguimientoAvicola/editarEstadoSalud";
    }

    //Eliminar estado de salud
    @GetMapping("/eliminarEstadoSalud/{idEstadoSalud}")
    public String eliminarEstado(@PathVariable("idEstadoSalud") Long idEstado, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        estadoSaludService.eliminarEstado(idEstado);
        System.out.println("Registro eliminado con exito!");
        attribute.addFlashAttribute("warning", "Estado de Salud Eliminado con Exito!");
        return "redirect:/consultarEstadoSalud";
    }
}
