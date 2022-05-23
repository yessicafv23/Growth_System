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
import com.Growth_System.Entity.Tipo_Enfermedad;
import com.Growth_System.Service.EnfermedadService;
import com.Growth_System.Service.TipoEnfermedadService;

@Controller
public class EnfermedadesController {

    @Autowired
    EnfermedadService service;

    @Autowired
    TipoEnfermedadService service2;

    @Autowired
    UsuarioPersonalizadoService usuarioPersonalizadoService;

    @GetMapping("/consultarEnfermedades")
    public String getConsultarEnfermedades(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        model.addAttribute("enfermedades", service.enfermedadListado());

        return "system/1-Administrador/modules/saludAvicola/Consultar_enfermedades";
    }

    @GetMapping("/gestionarEnfermedades")
    public String getGestionarEnfermedades(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        Enfermedad enfermedades = new Enfermedad();

        Iterable<Tipo_Enfermedad> Tipenfermedad = service2.listar();

        model.addAttribute("enfermedad", enfermedades);
        model.addAttribute("tipoEnfermedad", Tipenfermedad);

        return "system/1-Administrador/modules/saludAvicola/Gestionar_enfermedades";
    }


    @PostMapping("/gestionarEnfermedades")
    public String createEnfermedad(@Validated @ModelAttribute("enfermedades") Enfermedad enfermedades, BindingResult result,
                                   ModelMap model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        if (result.hasErrors()) {
            model.addAttribute("enfermedades", enfermedades);
        } else {
            try {
                enfermedades.setEstado(1);
                service.createEnfermedad(enfermedades);
                System.out.println("El registro se ha guardado exitosamente!");
                attribute.addFlashAttribute("success", "El registro se ha guardado exitosamente!");
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("enfermedades", enfermedades);
                System.out.println("El ID ya se encuentra registrado!");
                attribute.addFlashAttribute("danger", "El ID ya se encuentra registrado!");
                model.addAttribute("enfermedades", service.enfermedadListado());
            }
        }
        model.addAttribute("enfermedades", service.enfermedadListado());
        return "redirect:/consultarEnfermedades";
    }


    @GetMapping("/editarEnf/{IDEnfermedad}")
    public String editar(@PathVariable("IDEnfermedad") Long IDEnfermedad, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if (!encontroUsuarios) {
            return "redirect:/NoAcceso";
        }
        Enfermedad enfermedades = service.buscarPorId(IDEnfermedad);

        Iterable<Tipo_Enfermedad> Tipenfermedad = service2.listar();

        model.addAttribute("enfermedad", enfermedades);
        model.addAttribute("tipoEnfermedad", Tipenfermedad);

        return "system/1-Administrador/modules/saludAvicola/Editar_enfermedades";
    }

    @PostMapping("/actualizarEnfermedad")
    public String updateEnfermedad(@Validated Enfermedad enfermedad, Model model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        enfermedad.setEstado(1);
        service.updateEnfermedad(enfermedad);
        model.addAttribute("enfermedades", enfermedad);
        model.addAttribute("enfermedades", service.enfermedadListado());

        System.out.println("El registro se ha actualizado exitosamente!");
        attribute.addFlashAttribute("info", "El registro se ha actualizado exitosamente!");

        return "redirect:/consultarEnfermedades";
    }

    @GetMapping("/eliminarEnf/{IDEnfermedad}")
    public String deleteEnfermedad(Enfermedad enf2, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        service.enfermedadEstado(enf2);

        attribute.addFlashAttribute("warning", "El registro se ha eliminado exitosamente!");

        return "redirect:/consultarEnfermedades";
    }





    /*------------------------ EMPLEADO ------------------------------------------------*/

    @GetMapping("/consultarEnfermedadesEmpleado")
    public String getConsultarEnfermedadesEmpleado(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("enfermedades", service.enfermedadListado());
        return "system/2-Empleado/modules/saludAvicola/Consultar_enfermedades";
    }

    @GetMapping("/gestionarEnfermedadesEmpleado")
    public String getGestionarEnfermedadesEmpleado(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Enfermedad enfermedades = new Enfermedad();
        Iterable<Tipo_Enfermedad> Tipenfermedad = service2.listar();
        model.addAttribute("enfermedad", enfermedades);
        model.addAttribute("tipoEnfermedad", Tipenfermedad);
        return "system/2-Empleado/modules/saludAvicola/Gestionar_enfermedades";
    }

    @PostMapping("/gestionarEnfermedadesEmpleado")
    public String createEnfermedadEmpleado(@Validated @ModelAttribute("enfermedades") Enfermedad enfermedades, BindingResult result,
                                           ModelMap model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        if (result.hasErrors()) {
            model.addAttribute("enfermedades", enfermedades);
            System.out.println("El ID es muy largo: (max 45 caracteres)!");
            attribute.addFlashAttribute("danger", "El ID es muy largo: (max 45 caracteres)!");
        } else {
            try {
                enfermedades.setEstado(1);
                service.createEnfermedad(enfermedades);
                System.out.println("El registro se ha guardado exitosamente!");
                attribute.addFlashAttribute("success", "El registro se ha guardado exitosamente!");
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("enfermedades", enfermedades);
                System.out.println("El ID ya se encuentra registrado!");
                attribute.addFlashAttribute("danger", "El ID ya se encuentra registrado!");
                model.addAttribute("enfermedades", service.enfermedadListado());
            }
        }
        model.addAttribute("enfermedades", service.enfermedadListado());
        return "redirect:/consultarEnfermedadesEmpleado";
    }


    @GetMapping("/editarEnfEmpleado/{IDEnfermedad}")
    public String editarEmpleado(@PathVariable("IDEnfermedad") Long IDEnfermedad, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Enfermedad enfermedades = service.buscarPorId(IDEnfermedad);

        Iterable<Tipo_Enfermedad> Tipenfermedad = service2.listar();

        model.addAttribute("enfermedad", enfermedades);
        model.addAttribute("tipoEnfermedad", Tipenfermedad);

        return "system/2-Empleado/modules/saludAvicola/Editar_enfermedades";
    }

    @PostMapping("/actualizarEnfermedadEmpleado")
    public String updateEnfermedad2(@Validated Enfermedad enfermedad, Model model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        enfermedad.setEstado(1);
        service.updateEnfermedad(enfermedad);
        model.addAttribute("enfermedades", enfermedad);
        model.addAttribute("enfermedades", service.enfermedadListado());

        System.out.println("El registro se ha actualizado exitosamente!");
        attribute.addFlashAttribute("info", "El registro se ha actualizado exitosamente!");
        return "redirect:/consultarEnfermedadesEmpleado";
    }

    @GetMapping("/eliminarEnfEmpleado/{IDEnfermedad}")
    public String deleteEnfermedadEmpleado(Enfermedad enf2, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        service.enfermedadEstado(enf2);

        attribute.addFlashAttribute("warning", "El registro se ha eliminado exitosamente!");

        return "redirect:/consultarEnfermedadesEmpleado";
    }


}
