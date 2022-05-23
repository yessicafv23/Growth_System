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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class EmpleadoController {

    private final UsuarioRepository usuarioRepository;
    private final GalponService galponService;
    private final GastoService gastoService;
    private final ProductoService productoService;
    private final EnfermedadService enfermedadService;
    private final TratamientoService tratamientoService;
    private final CargoService cargoService;
    private final CiudadService ciudadService;
    private final TipoDocumentoService tipoDocumentoService;
    private final UsuarioPersonalizadoService usuarioPersonalizadoService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmpleadoController(UsuarioRepository usuarioRepository, GalponService galponService, GastoService gastoService, ProductoService productoService, EnfermedadService enfermedadService, TratamientoService tratamientoService, CargoService cargoService, CiudadService ciudadService, TipoDocumentoService tipoDocumentoService, UsuarioPersonalizadoService usuarioPersonalizadoService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.galponService = galponService;
        this.gastoService = gastoService;
        this.productoService = productoService;
        this.enfermedadService = enfermedadService;
        this.tratamientoService = tratamientoService;
        this.cargoService = cargoService;
        this.ciudadService = ciudadService;
        this.tipoDocumentoService = tipoDocumentoService;
        this.usuarioPersonalizadoService = usuarioPersonalizadoService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/Empleado")
    public String getUserEmpleado(Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        String username = authentication.getName();
        String nombreApellido = usuarioRepository.findByUsername(username).getNombre() + " " +
                usuarioRepository.findByUsername(username).getApellido();
        Long IDUsuario = usuarioRepository.findByUsername(username).getIDUsuario();
        model.addAttribute("nombre", nombreApellido);
        model.addAttribute("IDUsuario", IDUsuario);
        return "system/2-Empleado/indexEmpleado";
    }

    @GetMapping("/Empleado/inicio")
    public String getEmpleadoInicio(Authentication authentication, Model model) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Long cantidadPollos = this.galponService.cantidadPollos();
        String gastoTotal = changeFormat(this.gastoService.gatosTotal());
        Long cantidadProductos = productoService.totalProductos();
        Long salidasProductos = productoService.salidasProductos();
        Long enferRegistradas = enfermedadService.cantidadEnfermedades();
        Long cantidadTratamiento = tratamientoService.cantidadTratamiento();
        List<Object> tiempoImporte = funciontiempoImporte();
        List<Object> topProductos = this.productoService.topProductos();
        model.addAttribute("cantidadPollos", cantidadPollos);
        model.addAttribute("totalGasto", gastoTotal);
        model.addAttribute("cantidadProductos", cantidadProductos);
        model.addAttribute("salidasProductos", salidasProductos);
        model.addAttribute("enferRegistradas", enferRegistradas);
        model.addAttribute("cantidadTratamiento", cantidadTratamiento);
        model.addAttribute("tiempoImporte", tiempoImporte);
        model.addAttribute("topProductos", topProductos);
        return "system/2-Empleado/modules/inicio";
    }

    public List<Object> funciontiempoImporte(){
        LocalDate date = LocalDate.now();
        int anio = date.getYear();
        List<Object> arreglo = new ArrayList<>();
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


    @GetMapping("/Empleado/perfil")
    public String getEmpleadoPerfil(Authentication authResult, Usuario usuario, Model model, Authentication authentication) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        String username = authResult.getName();
        usuario = usuarioRepository.findByUsername(username);
        String nombreApellido = usuarioRepository.findByUsername(username).getNombre() + " " +
                usuarioRepository.findByUsername(username).getApellido();
        model.addAttribute("nombre", nombreApellido);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listTipodocumento", tipoDocumentoService.listaTipoDoc());
        model.addAttribute("listCiudad", ciudadService.listaCiudades());
        model.addAttribute("listCargo", cargoService.listaCargo());
        return "system/2-Empleado/modules/Usuario/perfil";
    }

    @PostMapping("/Empleado/actualizarPerfil")
    public String empleadoActualizarPerfil(Authentication authentication, Usuario usuario, Errors errores, RedirectAttributes attributes) {
        boolean encontroUsuarios = usuarioPersonalizadoService.verificarUsuario(authentication.getName());
        if(!encontroUsuarios){
            return "redirect:/NoAcceso";
        }
        Long id = usuario.getIDUsuario();
        if (errores.hasErrors()) {
            attributes.addFlashAttribute("mensaje", "Error no se pudo modificar los datos");
            attributes.addFlashAttribute("cambio", false);
            return "redirect:/Empleado/perfil?IDUsuario=" + id;
        }
        usuario.setRol("ROLE_USER");
        usuario.setEnabled(true);
        usuario.setEstadoUsu(1);
        this.usuarioRepository.save(usuario);
        attributes.addFlashAttribute("mensaje", "Datos modificados correctamente");
        attributes.addFlashAttribute("cambio", true);
        return "redirect:/Empleado/perfil?IDUsuario=" + id;
    }

    @PostMapping("/Empleado/actualizarClave")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String empleadoActualizarClave(@RequestParam(value = "password", required = false) String claveUsuario,
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
        return "redirect:/Empleado/perfil?IDUsuario=" + id;
    }
}
