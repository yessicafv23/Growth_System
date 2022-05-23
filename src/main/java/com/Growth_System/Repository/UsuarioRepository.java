package com.Growth_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Growth_System.Entity.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM growth_system.usuario WHERE IDUsuario != 1 AND rol != 'ROLE_ADMIN'", nativeQuery = true)
    List<Usuario> findAll();

    public Usuario findByUsername(String username);

    @Query(value = "SELECT u FROM Usuario u WHERE u.claveConfirmacion = ?1")
    public Usuario findByVerificationCode(String code);

    @Query(value = "SELECT u FROM Usuario u WHERE u.username = ?1")
    public Usuario findByEmail(String email);

    @Query(value = "SELECT username FROM Usuario WHERE IDUsuario = ?1", nativeQuery = true)
    public String findByIDEmail(Long id);

    @Query(value = "SELECT u.IDUsuario FROM Usuario u WHERE u.username = ?1")
    public Long findbyEmailID(String email);

    @Query(value = "SELECT COUNT(IDUsuario) FROM growth_system.usuario WHERE IDUsuario != 1", nativeQuery = true)
    public Long contadorUsuario();
}
