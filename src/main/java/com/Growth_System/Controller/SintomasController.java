package com.Growth_System.Controller;


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

import com.Growth_System.Entity.Enfermedad;
import com.Growth_System.Entity.Galpon;
import com.Growth_System.Entity.Sintoma;
import com.Growth_System.Service.EnfermedadService;
import com.Growth_System.Service.GalponService;
import com.Growth_System.Service.SintomaService;

@Controller
public class SintomasController {

    @Autowired
    SintomaService service;

    @Autowired
    GalponService service2;

    @Autowired
    EnfermedadService service3;

    @Autowired
    UsuarioPersonalizadoService usuarioPersonalizadoService;

    @GetMapping("/consultarSintomas")
    public String getConsultarSintomas(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        model.addAttribute("sintomas", service.sintomaListado());

        return "system/1-Administrador/modules/saludAvicola/Consultar_sintomas";
    }


    /*------------------------ EMPLEADO ------------------------------------------------*/


    @GetMapping("/consultarSintomasEmpleado")
    public String getConsultarSintomasEmpleado(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        model.addAttribute("sintomas", service.sintomaListado());

        return "system/2-Empleado/modules/saludAvicola/Consultar_prevenciones";
    }

    @GetMapping("/gestionarSintomasEmpleados")
    public String getGestionarSintomasEmpleado(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        Sintoma sintomas = new Sintoma();

        Iterable<Enfermedad> listaEnfermedades = service3.enfermedadListado();
        Iterable<Galpon> listaGalpones = service2.listaGalpon();

        model.addAttribute("enfermedad", listaEnfermedades);
        model.addAttribute("sintoma", sintomas);
        model.addAttribute("galpon", listaGalpones);

        return "system/2-Empleado/modules/saludAvicola/Registro_de_sintomas";
    }

    @PostMapping("/gestionarSintomasEmpleados")
    public String createSintoma(@Validated @ModelAttribute("sintomas") Sintoma sint, BindingResult result,
                                ModelMap model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        if (result.hasErrors()) {
            model.addAttribute("sintomas", sint);
        } else {
            try {
                sint.setEstado(1);
                service.createSintoma(sint);
                System.out.println("El registro se ha guardado exitosamente!");
                attribute.addFlashAttribute("success", "El registro se ha guardado exitosamente!");
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("sintomas", sint);
                System.out.println("El ID ya se encuentra registrado!");
                attribute.addFlashAttribute("danger", "El ID ya se encuentra registrado!");
                model.addAttribute("sintomas", service.sintomaListado());
            }
        }

        model.addAttribute("sintomas", service.sintomaListado());
        return "redirect:/consultarSintomasEmpleado";
    }

    @GetMapping("/editarSint/{IDSintoma}")
    public String editar(@PathVariable("IDSintoma") Long IDSintoma, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        Sintoma sintomas = service.buscarPorId(IDSintoma);

        Iterable<Galpon> listaGalpones = service2.listaGalpon();
        Iterable<Enfermedad> listaEnfermedades = service3.enfermedadListado();


        model.addAttribute("sintoma", sintomas);
        model.addAttribute("galpon", listaGalpones);
        model.addAttribute("enfermedad", listaEnfermedades);

        return "system/2-Empleado/modules/saludAvicola/Editar_sintomas";
    }

    @PostMapping("/actualizarSintoma")
    public String updateSintoma(@Validated Sintoma sintma, Model model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        sintma.setEstado(1);
        service.updateSintoma(sintma);
        model.addAttribute("sintomas", sintma);
        model.addAttribute("sintomas", service.sintomaListado());

        System.out.println("El registro se ha actualizado exitosamente!");
        attribute.addFlashAttribute("info", "El registro se ha actualizado exitosamente!");
        return "redirect:/consultarSintomasEmpleadoo";
    }

    @GetMapping("/eliminarSint/{IDSintoma}")
    public String deleteSintoma(Sintoma sint2, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        service.sintomaEstado(sint2);

        attribute.addFlashAttribute("warning", "El registro se ha eliminado exitosamente!");
        return "redirect:/consultarSintomasEmpleado";
    }

}
