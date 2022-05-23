package com.Growth_System.Login;

import com.Growth_System.Service.UsuarioPersonalizadoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Bean
    public UsuarioPersonalizadoService getUsuarioPersonalizadoService(){
        return new UsuarioPersonalizadoService();
    }

    @Bean
    public BCryptPasswordEncoder getPassword(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(getUsuarioPersonalizadoService());
        dao.setPasswordEncoder(getPassword());
        return dao;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    //Sobreescribo los métodos para el login (autorización)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/consultarTratamientosEmpleado", "/consultarGalpon", "/editarGalpon/**",
                        "/registrarGalpon", "/guardarGalpon", "/actualizarGalpon", "/eliminarGalpon/**", "/editarEstadoSalud/**",
                        "/eliminarEstadoSalud/**", "/asignarEstadoSalud", "/consultarSeguimiento", "/guardarEstadoSalud",
                        "/editarSeguimiento/**", "/eliminarSeguimiento/**", "/guardarSeguimiento", "/actualizarEnfermedad").hasAnyRole("ADMIN", "USER")

                .antMatchers("/Admin/**", "/consultarEnfermedades", "/gestionarEnfermedades", "/editarEnf/**"
                        , "/eliminarEnf/**", "/consultarEstadoSalud", "/actualizarEstado", "/consultarGastos", "/registrarGasto", "/guardarGasto",
                        "/editarGasto/**", "/eliminarGasto/**", "/consultarPrevenciones", "/gestionarPrevenciones",
                        "/gestionarPrevenciones", "/editarPrev/**", "/actualizarPrevencion", "/eliminarPrev/**",
                        "/registrarSeguimiento", "/consultarSintomas", "/consultarTratamientos", "/crearTratamientos",
                        "/crearTratamientos", "/editarTrat/**", "/actualizarTratamiento", "/eliminarTrat/**").hasRole("ADMIN")

                .antMatchers("/Empleado/**", "/consultarEnfermedadesEmpleado", "/gestionarEnfermedadesEmpleado",
                        "/editarEnfEmpleado/**", "/actualizarEnfermedadEmpleado", "/eliminarEnfEmpleado/**", "/consultarPrevencionesEmpleado", "/consultarSintomasEmpleado", "/gestionarSintomasEmpleados", "/gestionarSintomasEmpleados",
                        "/editarSint/**", "/actualizarSintoma", "/eliminarSint/**").hasRole("USER")


                .antMatchers("/css/**", "/js/**", "/img/**", "**/favicon.ico").permitAll()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/success", true)
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/error/error")
                .and()
                .headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable()
        ;
    }
}
