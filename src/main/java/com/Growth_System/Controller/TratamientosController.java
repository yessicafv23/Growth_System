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
import com.Growth_System.Entity.Tipo_Tratamiento;
import com.Growth_System.Entity.Tratamiento;
import com.Growth_System.Service.EnfermedadService;
import com.Growth_System.Service.GalponService;
import com.Growth_System.Service.TipoTratamientoService;
import com.Growth_System.Service.TratamientoService;

@Controller
public class TratamientosController {
	
	@Autowired
	TratamientoService service;
	
	@Autowired
	TipoTratamientoService service2;
	
	@Autowired
	EnfermedadService service3;
	
	@Autowired
	GalponService service4;

	@Autowired
	UsuarioPersonalizadoService usuarioPersonalizadoService;
	
	@GetMapping("/consultarTratamientos")
	public String getConsultarTratamientos(Model model, Authentication authentication) {
		boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
		if(!encontroUsuarios){
			return "redirect:/NoAcceso";
		}
		model.addAttribute("tratamientos", service.tratamientoListado());
		
		 return "system/1-Administrador/modules/saludAvicola/Consultar_tratamientos";
	}
	
	@GetMapping("/crearTratamientos")
	public String getCrearTratamientos(Model model, Authentication authentication) {
		boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
		if(!encontroUsuarios){
			return "redirect:/NoAcceso";
		}
		Tratamiento tratamientos = new Tratamiento();
		
		Iterable<Tipo_Tratamiento> Tiptratamiento = service2.listar();
		Iterable<Enfermedad> listaEnfermedades = service3.enfermedadListado();
		Iterable<Galpon> listaGalpones = service4.listaGalpon();
		
		model.addAttribute("enfermedad", listaEnfermedades);
		
		model.addAttribute("tratamiento", tratamientos);
		model.addAttribute("tipoTratamiento", Tiptratamiento);
		model.addAttribute("galpon", listaGalpones);
		
	return "system/1-Administrador/modules/saludAvicola/Crear_tratamientos";
	}
	
	@PostMapping("/crearTratamientos")
	public String createTratamiento(@Validated @ModelAttribute("tratamientos") Tratamiento trat, BindingResult result, 
			ModelMap model, RedirectAttributes attribute, Authentication authentication) {
		boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
		if(!encontroUsuarios){
			return "redirect:/NoAcceso";
		}
		if (result.hasErrors()) {
			model.addAttribute("tratamientos",trat);
		}else {
			try {
				trat.setEstado(1);
				service.createTratamiento(trat);
				System.out.println("El registro se ha guardado exitosamente!");
				attribute.addFlashAttribute("success", "El registro se ha guardado exitosamente!");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("tratamientos", trat);
				System.out.println("El ID ya se encuentra registrado!");
				attribute.addFlashAttribute("danger", "El ID ya se encuentra registrado!");
				 model.addAttribute("tratamientos", service.tratamientoListado());
				 model.addAttribute("enfermedades", service3.enfermedadListado());
			}
		}

		
		model.addAttribute("tratamientos", service.tratamientoListado());
		return "redirect:/consultarTratamientos";
	}
	
	
	@GetMapping("/editarTrat/{IDTratamiento}")
	public String editar(@PathVariable("IDTratamiento") Long IDTratamiento, Model model, Authentication authentication) {
		boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
		if(!encontroUsuarios){
			return "redirect:/NoAcceso";
		}
		Tratamiento tratamientos = service.buscarPorId(IDTratamiento);
		
		Iterable<Tipo_Tratamiento> Tiptratamiento = service2.listar();
		Iterable<Enfermedad> listaEnfermedades = service3.enfermedadListado();
		Iterable<Galpon> listaGalpones = service4.listaGalpon();
		
		model.addAttribute("enfermedad", listaEnfermedades);
		model.addAttribute("tratamiento", tratamientos);
		model.addAttribute("tipoTratamiento", Tiptratamiento);
		model.addAttribute("galpon", listaGalpones);
		
	return "system/1-Administrador/modules/saludAvicola/Editar_tratamientos";
	}
	
	@PostMapping("/actualizarTratamiento")
	public String updateTratamiento(@Validated Tratamiento Tratmients, Model model, RedirectAttributes attribute, Authentication authentication) {
		boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
		if(!encontroUsuarios){
			return "redirect:/NoAcceso";
		}
		Tratmients.setEstado(1);
		service.updateTratamiento(Tratmients);
		model.addAttribute("tratamientos", Tratmients);
		model.addAttribute("tratamientos", service.tratamientoListado());

		System.out.println("El registro se ha actualizado exitosamente!");
		attribute.addFlashAttribute("info", "El registro se ha actualizado exitosamente!");
		return "redirect:/consultarTratamientos";
	}
	
	@GetMapping("/eliminarTrat/{IDTratamiento}")
	public String deleteTratamiento (Tratamiento trat2 , RedirectAttributes attribute, Authentication authentication) {
		boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
		if(!encontroUsuarios){
			return "redirect:/NoAcceso";
		}
		
		service.tratamientoEstado(trat2);
		attribute.addFlashAttribute("warning", "El registro se ha eliminado exitosamente!");
		
	return "redirect:/consultarTratamientos";
	}
	
	
	/*------------------------ EMPLEADO ------------------------------------------------*/

	@GetMapping("/consultarTratamientosEmpleado")
	public String getConsultarTratamientosEmpleado(Model model, Authentication authentication) {
		boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
		if(!encontroUsuarios){
			return "redirect:/NoAcceso";
		}
		model.addAttribute("tratamientos", service.tratamientoListado());
		
		 return "system/2-Empleado/modules/saludAvicola/Consultar_tratamientos";
	}

}
