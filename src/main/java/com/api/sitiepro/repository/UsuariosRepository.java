package com.api.sitiepro.repository;

import com.api.sitiepro.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Usuarios findByCorreoUsuario(String correoUsuario);
}
