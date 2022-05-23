package com.Growth_System.Service;

import com.Growth_System.Entity.Usuario;
import com.Growth_System.Repository.UsuarioRepository;
import com.Growth_System.Utilidades.ManejoContenidoEmail;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class UsuarioPersonalizadoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ManejoContenidoEmail manejoEmail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario user =  usuarioRepository.findByUsername(username);

            if( user == null){
                throw new UsernameNotFoundException("No se encontró usuario");
            }else {
                return new UsuarioPersonalizado(user);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean concidenciaCorreo(Usuario usuario){
        Usuario esUsuario = this.usuarioRepository.findByUsername(usuario.getUsername());
        if(esUsuario != null && esUsuario.isEnabled() == true){
            return true;
        }else{
           return false;
        }
    }

    public Usuario encontrarUsuario(Usuario usuario){
        return this.usuarioRepository.findById(usuario.getIDUsuario()).orElse(null);
    }

    public Usuario encontrarUsuarioCorreo(String correo){
        return this.usuarioRepository.findByEmail(correo);
    }

    public boolean verificarUsuario(String correo){
        try {
            Usuario usuario = encontrarUsuarioCorreo(correo);
            if(usuario.isEnabled() != true || !(usuario.getRol().equals("ROLE_ADMIN") || usuario.getRol().equals("ROLE_USER"))){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Long encontrarID(String correo){
        return this.usuarioRepository.findbyEmailID(correo);
    }

    public void registro(Usuario usuario, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String encodedPassword = bCryptPasswordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);

        String randomCode = RandomString.make(64);
        usuario.setClaveConfirmacion(randomCode);
        usuario.setEnabled(false);
        usuario.setRol("ROLE_VALIDACION");
        usuarioRepository.save(usuario);

        sendVerificationEmail(usuario, siteURL);
    }

    public void sendVerificationEmail(Usuario usuario, String sitioURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = usuario.getUsername();
        String fromAddress = "growthsystemsena@gmail.com";
        String senderName = "Growth System";
        String subject = "Por favor verificar tu registro";
        String content = manejoEmail.envioEmail(true, null);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", usuario.getNombre());
        String verifyURL = sitioURL + "/verify?code=" + usuario.getClaveConfirmacion();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    public boolean verify(String verificationCode){
       Usuario usuario = usuarioRepository.findByVerificationCode(verificationCode);

        if (usuario == null || usuario.isEnabled()){
            return false;
        }else{
            usuario.setClaveConfirmacion(null);
            usuario.setEnabled(true);
            usuario.setRol("ROLE_INDEFINIDO");
            usuarioRepository.save(usuario);
            return true;
        }
    }

    public void tokenReinicioClave(String token, String email){
        Usuario usuario = usuarioRepository.findByEmail(email);
        if(usuario != null && usuario.getClaveConfirmacion() == null && usuario.isEnabled()) {
            usuario.setClaveConfirmacion(token);
            usuarioRepository.save(usuario);
        }else if(usuario != null && usuario.getClaveConfirmacion() != null && usuario.isEnabled()){
            usuario.setClaveConfirmacion(token);
            usuarioRepository.save(usuario);
        }else if(usuario != null && usuario.getClaveConfirmacion() != null && !(usuario.isEnabled())){
            throw new UsernameNotFoundException("Valide su correo primero para poder recuperar su clave");
        }else{
            throw new UsernameNotFoundException("No se encontro el usuario con correo: " + email);
        }
    }

    public void enviarEmail(String contenedorEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("growthsystemsena@gmail.com", "Centro de administracion");
        helper.setTo(contenedorEmail);
        String subject = "Link para recuperar tu clave";
        String contenido = manejoEmail.envioEmail(false, link);
        helper.setSubject(subject);
        helper.setText(contenido, true);
        mailSender.send(message);
    }

    public Usuario obtenerTokenClave(String token){
        return usuarioRepository.findByVerificationCode(token);
    }

    public void actualizarClave(Usuario usuario, String nuevaClave){
        String claveEncriptada = bCryptPasswordEncoder.encode(nuevaClave);
        usuario.setPassword(claveEncriptada);

        usuario.setClaveConfirmacion(null);
        usuarioRepository.save(usuario);
    }

    public Long contadorUsuario(){
        return usuarioRepository.contadorUsuario();
    };
}
