package com.api.sitiepro.repository;

import com.api.sitiepro.entity.TokenAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepositoryAdmin extends JpaRepository<TokenAdmin, Long> {

    @Query("""
    Select tt from TokenAdmin tt inner join Usuarios us
    on tt.usuario.idUsuario = us.idUsuario
    where tt.usuario.idUsuario = :idUsuario and tt.loggedOutAdmin = false 
""")
    List<TokenAdmin> findAllTokenByUsuario(Long idUsuario);

    Optional<TokenAdmin> findByTokenAdmin(String tokenAdmin);
}
