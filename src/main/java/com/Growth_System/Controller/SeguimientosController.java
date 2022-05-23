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

import com.Growth_System.Entity.Dias;
import com.Growth_System.Entity.Raza;
import com.Growth_System.Entity.Seguimiento_raza;
import com.Growth_System.Service.DiasService;
import com.Growth_System.Service.RazaService;
import com.Growth_System.Service.SeguimientoService;

@Controller
public class SeguimientosController {
    @Autowired
    SeguimientoService seguimientoService;

    @Autowired
    DiasService diasService;

    @Autowired
    RazaService razaService;

    @Autowired
    UsuarioPersonalizadoService usuarioPersonalizadoService;

    @GetMapping("/consultarSeguimiento")
    public String listarSeguimiento(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        List<Seguimiento_raza> listadoSeguimientos = seguimientoService.listarTodos();
        model.addAttribute("seguimientos", listadoSeguimientos);
        return "system/1-Administrador/modules/SeguimientoAvicola/consultarSeguimiento";
    }

    @GetMapping("/registrarSeguimiento")
    public String registrarSeguimiento(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Seguimiento_raza seguimiento_raza = new Seguimiento_raza();
        List<Raza> listaRazas = razaService.listaRaza();
        List<Dias> listaDias = diasService.listaDias();

        model.addAttribute("seguimiento_raza", seguimiento_raza);
        model.addAttribute("razas", listaRazas);
        model.addAttribute("dias", listaDias);
        return "system/1-Administrador/modules/SeguimientoAvicola/registrar_seguimiento";
    }

    @PostMapping("/guardarSeguimiento")
    public String guardarSeguimiento(@ModelAttribute Seguimiento_raza seguimiento_raza, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        seguimientoService.guardarSeguimiento(seguimiento_raza);
        System.out.println("Seguimiento guardado con exito!");
        attribute.addFlashAttribute("success", "Seguimiento guardado correctamente");
        return "redirect:/consultarSeguimiento";
    }

    @GetMapping("/editarSeguimiento/{idseguimiento_raza}")
    public String editarSeguimiento(@PathVariable("idseguimiento_raza") Long idSeguimiento, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Seguimiento_raza seguimiento_raza = seguimientoService.buscarPorId(idSeguimiento);
        List<Raza> listaRazas = razaService.listaRaza();
        List<Dias> listaDias = diasService.listaDias();

        model.addAttribute("seguimiento_raza", seguimiento_raza);
        model.addAttribute("razas", listaRazas);
        model.addAttribute("dias", listaDias);
        return "system/1-Administrador/modules/SeguimientoAvicola/registrar_seguimiento";
    }

    @GetMapping("/eliminarSeguimiento/{idseguimiento_raza}")
    public String eliminarSeguimiento(@PathVariable("idseguimiento_raza") Long idSeguimiento, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        seguimientoService.eliminarSeguimiento(idSeguimiento);
        System.out.println("Seguimiento eliminado con exito!");
        attribute.addFlashAttribute("warning", "Seguimiento Eliminado con Exito!");
        return "redirect:/consultarSeguimiento";
    }

}
