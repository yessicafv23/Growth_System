package com.Growth_System.Controller;

import java.util.List;

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
import com.Growth_System.Entity.Gasto;
import com.Growth_System.Entity.Tipo_gasto;
import com.Growth_System.Service.GalponService;
import com.Growth_System.Service.GastoService;
import com.Growth_System.Service.TipoGastoService;

@Controller
public class GastosController {

    @Autowired
    private GastoService gastoService;

    @Autowired
    private TipoGastoService tipoGastoService;

    @Autowired
    private GalponService galponService;

    @Autowired
    private UsuarioPersonalizadoService usuarioPersonalizadoService;

    @GetMapping("/consultarGastos")
    public String listarGastos(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        model.addAttribute("gastos", gastoService.listaGasto());
        return "system/1-Administrador/modules/SeguimientoAvicola/consultarGastos";
    }

    @GetMapping("/registrarGasto")
    public String getRegistrarGasto(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Gasto gasto = new Gasto();

        List<Tipo_gasto> listaTipoGastos = tipoGastoService.listaGastos();
        List<Galpon> listaGalpones = galponService.listaGalpon();

        model.addAttribute("gasto", gasto);
        model.addAttribute("tipoGastos", listaTipoGastos);
        model.addAttribute("galpones", listaGalpones);
        return "system/1-Administrador/modules/SeguimientoAvicola/registrar_gastos";
    }

    @PostMapping("/guardarGasto")
    public String guardarGasto(@Validated @ModelAttribute("gastos") Gasto gastos, BindingResult result,
                               ModelMap model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        if (result.hasErrors()) {
            model.addAttribute("gastos", gastos);
        } else {
            try {
                gastos.setEstadoGasto("activo");
                gastoService.registrarGasto(gastos);
                attribute.addFlashAttribute("success", "El gasto se ha guardado con exito");
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                model.addAttribute("gastos", gastos);
                attribute.addFlashAttribute("danger", "El código  del gasto ya se encuentra registrado!");
                model.addAttribute("gastos", gastoService.listaGasto());
            }
        }

        model.addAttribute("gastos", gastoService.listaGasto());
        return "redirect:/consultarGastos";
    }


    //Editar gasto
    @GetMapping("/editarGasto/{IDGasto}")
    public String editarGastos(@PathVariable("IDGasto") Long IDGasto, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Gasto gasto = gastoService.buscarPorId(IDGasto);

        List<Tipo_gasto> listaTipoGastos = tipoGastoService.listaGastos();
        List<Galpon> listaGalpones = galponService.listaGalpon();

        model.addAttribute("gasto", gasto);

        model.addAttribute("tipoGastos", listaTipoGastos);
        model.addAttribute("galpones", listaGalpones);
        return "system/1-Administrador/modules/SeguimientoAvicola/editarGasto";
    }

    @PostMapping("/actualizarGasto")
    public String actualizarGasto(@Validated Gasto gasto, Model model, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        System.out.println(gasto);
        gasto.setEstadoGasto("activo");
        gastoService.actualizarGasto(gasto);
        model.addAttribute("gastos", gasto);
        model.addAttribute("gastos", gastoService.listaGasto());

        System.out.println("El gasto se ha actualizado exitosamente");
        attribute.addFlashAttribute("success", "El gasto se ha actualizado exitosamente.");

        return "redirect:/consultarGastos";
    }

    //Eliminar gasto
    @GetMapping("/eliminarGasto/{IDGasto}")
    public String eliminarGastos(Gasto EliminarGasto, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        gastoService.eliminarGasto(EliminarGasto);

        attribute.addFlashAttribute("warning", "El gasto se ha eliminado exitosamente.");
        return "redirect:/consultarGastos";
    }

}
