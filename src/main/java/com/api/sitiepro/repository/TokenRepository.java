package com.api.sitiepro.repository;

import com.api.sitiepro.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
            Select t from Token t inner join Usuarios u
            on t.usuarios.idUsuario = u.idUsuario
            where t.usuarios.idUsuario = :userId and t.loggedOut = false
    """)
    List<Token> findAllTokenByUser(Long userId);

    Optional<Token> findByToken(String token);
}

