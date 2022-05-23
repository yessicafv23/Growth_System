package com.Growth_System.Controller;

import java.util.List;

import com.Growth_System.Repository.UsuarioRepository;
import com.Growth_System.Service.UsuarioPersonalizadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Growth_System.Entity.Galpon;
import com.Growth_System.Entity.Raza;
import com.Growth_System.Service.GalponService;
import com.Growth_System.Service.RazaService;

@Controller
public class GalponController {

    @Autowired
    GalponService galponService;

    @Autowired
    RazaService razaService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioPersonalizadoService usuarioPersonalizadoService;

    @GetMapping("/consultarGalpon")
    public String consultarGalpones(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("galpones", galponService.listaGalpon());

        return "system/1-Administrador/modules/SeguimientoAvicola/consultarGalpon";
    }


    @GetMapping("/registrarGalpon")
    public String registrarGalpon(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        String username = authentication.getName();
        Long idUsuario = usuarioRepository.findByUsername(username).getIDUsuario();
        Galpon galpon = new Galpon();

        List<Raza> listaRazas = razaService.listaRaza();

        model.addAttribute("galpon", galpon);
        model.addAttribute("razas", listaRazas);
        model.addAttribute("IDUsuario", idUsuario);
        return "system/2-Empleado/modules/SeguimientoAvicola/registrar_galpon";
    }

    @PostMapping("/guardarGalpon")
    public String guardarGalpon(@Validated @ModelAttribute("galpones") Galpon galpones, BindingResult result,
                                ModelMap model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        if (result.hasErrors()) {
            model.addAttribute("galpones", galpones);
        } else {
            try {
                galpones.setEstadoGalpon("produccion");
                galponService.registrarGalpon(galpones);
                attribute.addFlashAttribute("success", "El galpón se ha guardado con exito");
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("galpones", galpones);
                attribute.addFlashAttribute("danger", "El código  del galpón ya se encuentra registrado!");
                model.addAttribute("galpones", galponService.listaGalpon());
            }
        }

        model.addAttribute("galpones", galponService.listaGalpon());

        return "redirect:/registrarGalpon";
    }


    @GetMapping("/editarGalpon/{IDGalpon}")
    public String editarGalpon(@PathVariable("IDGalpon") Long IDGalpon, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Galpon galpones = galponService.buscarPorId(IDGalpon);

        List<Raza> listaRazas = razaService.listaRaza();

        model.addAttribute("galpon", galpones);
        model.addAttribute("razas", listaRazas);
        return "system/1-Administrador/modules/SeguimientoAvicola/editarGalpon";
    }

    @PostMapping("/actualizarGalpon")
    public String actualizarGalpon(@Validated Galpon galpon, Model model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        galpon.setEstadoGalpon("produccion");
        galponService.actualizarGalpon(galpon);
        model.addAttribute("galpones", galpon);
        model.addAttribute("galpones", galponService.listaGalpon());

        System.out.println("El galpón se ha actualizado correctamente!");
        attribute.addFlashAttribute("success", "El galpón se ha actualizado correctamente!");

        return "redirect:/consultarGalpon";
    }

    @GetMapping("/eliminarGalpon/{IDGalpon}")
    public String eliminarGalpon(Galpon EliminarGalpon, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        galponService.eliminarGalpon(EliminarGalpon);

        attribute.addFlashAttribute("warning", "El registro se ha eliminado exitosamente!");
        return "redirect:/consultarGalpon";
    }

}
