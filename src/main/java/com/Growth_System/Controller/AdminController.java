package com.Growth_System.Controller;

import com.Growth_System.Entity.Usuario;
import com.Growth_System.Repository.UsuarioRepository;
import com.Growth_System.Service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class AdminController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioPersonalizadoService usuarioPersonalizadoService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CargoService cargoService;
    private final CiudadService ciudadService;
    private final TipoDocumentoService tipoDocumentoService;
    private final GalponService galponService;
    private final GastoService gastoService;
    private final ProductoService productoService;
    private final EnfermedadService enfermedadService;
    private final TratamientoService tratamientoService;

    public AdminController(UsuarioRepository usuarioRepository, UsuarioPersonalizadoService usuarioPersonalizadoService, BCryptPasswordEncoder bCryptPasswordEncoder, CargoService cargoService, CiudadService ciudadService, TipoDocumentoService tipoDocumentoService, GalponService galponService, GastoService gastoService, ProductoService productoService, EnfermedadService enfermedadService, TratamientoService tratamientoService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioPersonalizadoService = usuarioPersonalizadoService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cargoService = cargoService;
        this.ciudadService = ciudadService;
        this.tipoDocumentoService = tipoDocumentoService;
        this.galponService = galponService;
        this.gastoService = gastoService;
        this.productoService = productoService;
        this.enfermedadService = enfermedadService;
        this.tratamientoService = tratamientoService;
    }



    @GetMapping("/Admin")
    public String getUserAdmin(Authentication authentication, Model model) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        String username = authentication.getName();
        String nombreApellido = this.usuarioRepository.findByUsername(username).getNombre() + " " +
                this.usuarioRepository.findByUsername(username).getApellido();
        Long IDUsuario = this.usuarioRepository.findByUsername(username).getIDUsuario();
        model.addAttribute("nombre", nombreApellido);
        model.addAttribute("IDUsuario", IDUsuario);
        return "system/1-Administrador/indexAdmin";
    }


    @GetMapping("/Admin/inicio")
    public String getAdminInicio(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Long cantidadPollos = this.galponService.cantidadPollos();
        String gastoTotal = changeFormat(this.gastoService.gatosTotal());
        Long cantidadProductos = productoService.totalProductos();
        Long enferRegistradas = enfermedadService.cantidadEnfermedades();
        Long cantidadTratamiento = tratamientoService.cantidadTratamiento();
        List<Object> tiempoImporte = funciontiempoImporte();
        List<Object> topProductos = this.productoService.topProductos();
        Long contar = usuarioPersonalizadoService.contadorUsuario();
        model.addAttribute("cantidadPollos", cantidadPollos);
        model.addAttribute("totalGasto", gastoTotal);
        model.addAttribute("cantidadProductos", cantidadProductos);
        model.addAttribute("enferRegistradas", enferRegistradas);
        model.addAttribute("cantidadTratamiento", cantidadTratamiento);
        model.addAttribute("tiempoImporte", tiempoImporte);
        model.addAttribute("topProductos", topProductos);
        model.addAttribute("contarUsuario", contar);
        return "system/1-Administrador/modules/inicio";
    }

    public List<Object> funciontiempoImporte(){
        LocalDate date = LocalDate.now();
        int anio = date.getYear();
        List<String> arreglo = new ArrayList<>();
        arreglo.add("Mes");
        arreglo.add("Importe");
        List<Object> tiempoImporte = gastoService.gastosMesPorYear(anio);
        tiempoImporte.add(0, arreglo);
        return tiempoImporte;
    }

    public String changeFormat(Long valor){
        Locale cop = new Locale("es", "CO");
        NumberFormat pesosFormato = NumberFormat.getCurrencyInstance(cop);
        return pesosFormato.format(valor);
    }

    @GetMapping("/Admin/perfil")
    public String getAdminPerfil(Authentication authentication, Usuario usuario, Model model) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        String username = authentication.getName();
        usuario = this.usuarioRepository.findByUsername(username);
        String nombreApellido = this.usuarioRepository.findByUsername(username).getNombre() + " " +
                this.usuarioRepository.findByUsername(username).getApellido();
        model.addAttribute("nombre", nombreApellido);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listTipodocumento", this.tipoDocumentoService.listaTipoDoc());
        model.addAttribute("listCiudad", this.ciudadService.listaCiudades());
        model.addAttribute("listCargo", this.cargoService.listaCargo());
        return "system/1-Administrador/modules/Usuario/perfil";
    }

    @GetMapping("/Admin/consultarUsuarios")
    public String getEmpleados(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        List<Usuario> listaUsuarios = this.usuarioRepository.findAll();
        model.addAttribute("usuarios", listaUsuarios);
        return "system/1-Administrador/modules/Usuario/consultarUsuarios";
    }

    @GetMapping("/Admin/editarUsuario/{IDUsuario}")
    public String editarUsuario(Usuario usuario, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        usuario = this.usuarioPersonalizadoService.encontrarUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listTipodocumento", this.tipoDocumentoService.listaTipoDoc());
        model.addAttribute("listCiudad", this.ciudadService.listaCiudades());
        model.addAttribute("listCargo", this.cargoService.listaCargo());
        return "system/1-Administrador/modules/Usuario/editarUsuario";
    }

    //Eliminar usuario
    @GetMapping("/Admin/eliminarUsuario/{IDUsuario}")
    public String eliminarUsuario(@PathVariable("IDUsuario") Long idUsuario, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        this.usuarioRepository.deleteById(idUsuario);
        attribute.addFlashAttribute("warning", "Usuario Eliminado con Éxito!");
        return "redirect:/Admin/consultarUsuarios";
    }

    @PostMapping("/Admin/actualizarUsuario")
    public String actualizarUsuario(@Valid Usuario usuario, Errors errores, RedirectAttributes attribute, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        if (errores.hasErrors()) {
            return "redirect:/Admin/consultarUsuarios";
        }
        usuario.setEnabled(true);
        usuario.setEstadoUsu(1);
        this.usuarioRepository.save(usuario);
        attribute.addFlashAttribute("success", "El usuario se ha actualizado correctamente!");
        return "redirect:/Admin/consultarUsuarios";
    }

    @PostMapping("/Admin/actualizarPerfil")
    public String adminActualizarPerfil(Authentication authentication, Usuario usuario, Errors errores, RedirectAttributes attributes) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Long id = usuario.getIDUsuario();
        if (errores.hasErrors()) {
            attributes.addFlashAttribute("mensaje", "Error no se pudo modificar los datos");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Admin/perfil?IDUsuario=" + id;
        }
        usuario.setRol("ROLE_ADMIN");
        usuario.setEnabled(true);
        usuario.setEstadoUsu(1);
        this.usuarioRepository.save(usuario);
        attributes.addFlashAttribute("mensaje", "Datos modificados correctamente");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Admin/perfil?IDUsuario=" + id;
    }

    @PostMapping("/Admin/actualizarClave")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String adminActualizarClave(@RequestParam(value = "password", required = false) String claveUsuario,
                                       @RequestParam(value = "passwordNew", required = false) String claveNueva,
                                       Authentication authentication, RedirectAttributes attributes) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        String usernameAuth = authentication.getName();
        Usuario usuario = this.usuarioPersonalizadoService.encontrarUsuarioCorreo(usernameAuth);
        Long id = usuario.getIDUsuario();
        String claveGuardada = usuario.getPassword();
        if (bCryptPasswordEncoder.matches(claveUsuario, claveGuardada)) {
            this.usuarioPersonalizadoService.actualizarClave(usuario, claveNueva);
            attributes.addFlashAttribute("mensaje", "Clave cambiada exitosamente");
            attributes.addFlashAttribute("cambio", true);
        } else {
            attributes.addFlashAttribute("mensaje", "Clave incorrecta, vuelva a intentarlo");
            attributes.addFlashAttribute("cambio", false);
        }
        return "redirect:/Admin/perfil?IDUsuario=" + id;
    }
}
