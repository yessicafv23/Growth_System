package com.Growth_System.Controller;

import com.Growth_System.Entity.*;
import com.Growth_System.Service.*;
import com.Growth_System.Repository.UsuarioRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Controller
public class LoginController {

    private final JavaMailSender javaMailSender;
    private final UsuarioPersonalizadoService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final TipoDocumentoService tipoDocumentoService;
    private final CiudadService ciudadService;
    private final CargoService cargoService;

    public LoginController(JavaMailSender javaMailSender, UsuarioPersonalizadoService usuarioService, UsuarioRepository usuarioRepository, TipoDocumentoService tipoDocumentoService, CiudadService ciudadService, CargoService cargoService) {
        this.javaMailSender = javaMailSender;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.tipoDocumentoService = tipoDocumentoService;
        this.ciudadService = ciudadService;
        this.cargoService = cargoService;
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(value="error", required = false) String error, Model model,
                           Principal principal, RedirectAttributes attributes){
        if( error != null ){
            model.addAttribute("error", "Usuario y/o Contraseña incorrecto, verifique su credenciales");
        }

        if( principal != null ){
            attributes.addFlashAttribute("Precaucion", "USTED YA HA INICIADO SESION PREVIAMENTE");
            return "redirect:/";
        }
        return "system/login";
    }

    @GetMapping("/registro")
    public String getRegistro(Model model, Tipo_documento tipo_documento){
        model.addAttribute("listDocumento", this.tipoDocumentoService.listaTipoDoc());
        model.addAttribute("listCiudad", this.ciudadService.listaCiudades());
        model.addAttribute("listCargo", this.cargoService.listaCargo());
        return "assets/registroUsuario";
    }

    @GetMapping("/Validacion")
    public String getValidacion(){
        return "system/verificacion";
    }

    @GetMapping("/ValidacionCorreo")
    public String getValidacionCorreo(){
        return "system/verificacionCorreo";
    }

    @GetMapping("/VerificacionExitosa")
    public String getVerificacionExitosa(){
        return "system/procesoCorreo/verificacionExitosa";
    }

    @GetMapping("/VerificacionFallida")
    public String getGetVerificacionFallida(){
        return "system/procesoCorreo/verificacionFallida";
    }

    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        String role = authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Admin"));
        }else if(role.contains("ROLE_USER")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Empleado"));
        }else if(role.contains("ROLE_INDEFINIDO")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()) + "/Validacion");
        } else{
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()) + "/Validacion");
        }
    }

    @PostMapping("/registrarse")
    public String registrarse(@ModelAttribute Usuario usuario, RedirectAttributes attributes, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        boolean hayUsuario = this.usuarioService.concidenciaCorreo(usuario);
        if(hayUsuario == true){
            attributes.addFlashAttribute("mensaje", "Este correo ya esta registrado, intente con un correo diferente");
            return "redirect:/registro";
        }else{
            this.usuarioService.registro(usuario, getSiteURL(request));
            return "redirect:/ValidacionCorreo";
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        if (this.usuarioService.verify(code)) {
            return "redirect:/VerificacionExitosa";
        }else{
            return "redirect:/VerificacionFallida";
        }
    }

    @GetMapping("/reiniciarClave")
    public String reiniciarClave(){
        return "assets/olvidoclave";
    }

    @PostMapping("/verificacionCorreo")
    public String verificacionCorreo(HttpServletRequest request, Model model) {
        String email = request.getParameter("correo");
        String token = RandomString.make(64);
        try {
            usuarioService.tokenReinicioClave(token, email);
            String linkReiniciarClave = getSiteURL(request) + "/reiniciar_clave?token=" + token;
            usuarioService.enviarEmail(email, linkReiniciarClave);
            model.addAttribute("mensaje", "Se ha enviado las instrucciones a su correo");
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error mientras se enviaba el email, inténtelo nuevamente");
        }
        return "assets/olvidoclave";
    }

    @GetMapping("/reiniciar_clave")
    public String formularioClave(@Param(value = "token") String token, Model model){
        Usuario usuario = usuarioService.obtenerTokenClave(token);
        model.addAttribute("token", token);
        if(usuario == null) {
            System.out.println("Token invalido");
            return "error/error";
        }
        return "assets/cambioclave";
    }

    @PostMapping("/procesoReinicio")
    public String procesoReiniciarClave(HttpServletRequest request, RedirectAttributes attributes){
        String token = request.getParameter("token");
        String clave = request.getParameter("password");
        Usuario usuario = usuarioService.obtenerTokenClave(token);

        if(usuario == null){
            attributes.addFlashAttribute("mensaje", "Error en el proceso de verificacion de token vuelva a intentarlo");
            attributes.addFlashAttribute("cambio", false);
        } else{
            usuarioService.actualizarClave(usuario, clave);
            attributes.addFlashAttribute("mensaje", "Clave cambiada exitosamente");
            attributes.addFlashAttribute("cambio", true);
        }
        return "redirect:/login";
    }

    @GetMapping("/NoAcceso")
    public String errorUsuarioEliminado(){
        return "error/error.html";
    }
}
