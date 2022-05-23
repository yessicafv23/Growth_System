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

import com.Growth_System.Entity.Prevencion;
import com.Growth_System.Entity.Sintoma;
import com.Growth_System.Service.PrevencionService;
import com.Growth_System.Service.SintomaService;

@Controller
public class PrevencionesController {

    @Autowired
    PrevencionService service;

    @Autowired
    SintomaService service2;

    @Autowired
    UsuarioPersonalizadoService usuarioPersonalizadoService;

    @GetMapping("/consultarPrevenciones")
    public String getConsultarPrevenciones(Model model) {
        model.addAttribute("prevenciones", service.prevencionListado());
        return "system/1-Administrador/modules/saludAvicola/Ver_prevenciones";
    }

    @GetMapping("/gestionarPrevenciones")
    public String getGestionarPrevenciones(Model model) {
        Prevencion prevenciones = new Prevencion();
        Iterable<Sintoma> listaSintomas = service2.sintomaListado();

        model.addAttribute("sintoma", listaSintomas);

        model.addAttribute("prevencion", prevenciones);

        return "system/1-Administrador/modules/saludAvicola/Registrar_prevenciones";
    }

    @PostMapping("/gestionarPrevenciones")
    public String createPrevencion(@Validated @ModelAttribute("prevenciones") Prevencion prev, BindingResult result,
                                   ModelMap model, RedirectAttributes attribute) {
        if (result.hasErrors()) {
            model.addAttribute("prevenciones", prev);
            System.out.println("El registro se ha guardado exitosamente!");
            attribute.addFlashAttribute("success", "El registro se ha guardado exitosamente!");
        } else {
            try {
                prev.setEstado(1);
                service.createPrevencion(prev);
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("prevenciones", prev);
                System.out.println("El ID ya se encuentra registrado!");
                attribute.addFlashAttribute("danger", "El ID ya se encuentra registrado!");
                model.addAttribute("prevenciones", service.prevencionListado());
            }
        }
        model.addAttribute("prevenciones", service.prevencionListado());
        return "redirect:/consultarPrevenciones";
    }


    @GetMapping("/editarPrev/{IDPrevencion}")
    public String editar(@PathVariable("IDPrevencion") Long IDPrevencion, Model model) {
        Prevencion prevenciones = service.buscarPorId(IDPrevencion);

        Iterable<Sintoma> listaSintomas = service2.sintomaListado();

        model.addAttribute("sintoma", listaSintomas);
        model.addAttribute("prevencion", prevenciones);


        return "system/1-Administrador/modules/saludAvicola/Editar_prevenciones";
    }

    @PostMapping("/actualizarPrevencion")
    public String updatePrevencion(@Validated Prevencion preven, Model model, RedirectAttributes attribute) {

        preven.setEstado(1);
        service.updatePrevencion(preven);
        model.addAttribute("prevenciones", preven);
        model.addAttribute("prevenciones", service.prevencionListado());

        System.out.println("El registro se ha actualizado exitosamente!");
        attribute.addFlashAttribute("info", "El registro se ha actualizado exitosamente!");
        return "redirect:/consultarPrevenciones";
    }


    @GetMapping("/eliminarPrev/{IDPrevencion}")
    public String deletePrevencion(Prevencion pre2, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        service.prevencionEstado(pre2);


        System.out.println("El registro se ha eliminado exitosamente!");
        attribute.addFlashAttribute("warning", "El registro se ha eliminado exitosamente!");
        return "redirect:/consultarPrevenciones";
    }

    /*------------------------ EMPLEADO ------------------------------------------------*/


    @GetMapping("/consultarPrevencionesEmpleado")
    public String getConsultarPrevencionesEmpleado(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("prevenciones", service.prevencionListado());

        return "system/2-Empleado/modules/saludAvicola/Ver_prevenciones";
    }


}
