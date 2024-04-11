package com.api.sitiepro.repository;

import com.api.sitiepro.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByCorreoUsuario(String email);
}
