package com.api.sitiepro.service;

import com.api.sitiepro.entity.AuthenticationResponseAdmin;
import com.api.sitiepro.entity.TokenAdmin;
import com.api.sitiepro.entity.Usuarios;
import com.api.sitiepro.repository.TokenRepositoryAdmin;
import com.api.sitiepro.repository.UsuariosRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceAdmin {
    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoderAdmin;
    private final JwtServiceAdmin jwtServiceAdmin;
    private final AuthenticationManager authenticationManagerAdmin;
    private final TokenRepositoryAdmin tokenRepositoryAdmin;

    public AuthenticationServiceAdmin(UsuariosRepository usuariosRepository,
                                      PasswordEncoder passwordEncoderAdmin,
                                      JwtServiceAdmin jwtServiceAdmin,
                                      AuthenticationManager authenticationManagerAdmin,
                                      TokenRepositoryAdmin tokenRepositoryAdmin) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoderAdmin = passwordEncoderAdmin;
        this.jwtServiceAdmin = jwtServiceAdmin;
        this.authenticationManagerAdmin = authenticationManagerAdmin;
        this.tokenRepositoryAdmin = tokenRepositoryAdmin;
    }

    public AuthenticationResponseAdmin registro(Usuarios request) {
        Usuarios usuarios = new Usuarios();
        usuarios.setRol(request.getRol());
        usuarios.setNombreUsuario(request.getNombreUsuario());
        usuarios.setCorreoUsuario(request.getCorreoUsuario());
        usuarios.setContrasenaUsuario(passwordEncoderAdmin.encode(request.getContrasenaUsuario()));

        usuarios = usuariosRepository.save(usuarios);

        String token = jwtServiceAdmin.generatedTokenAdmin(usuarios);

        revokeAllTokenByUsuario(usuarios);

        // guardar el token generado
        saveUsuarioToken(token, usuarios);

        return new AuthenticationResponseAdmin(token);
    }

    private void revokeAllTokenByUsuario(Usuarios usuarios) {
        List<TokenAdmin> validTokenListByUsuario = tokenRepositoryAdmin.findAllTokenByUsuario(usuarios.getIdUsuario());

        if(!validTokenListByUsuario.isEmpty()) {
            validTokenListByUsuario.forEach(t->{
                t.setLoggedOutAdmin(true);
            });
        }

        tokenRepositoryAdmin.saveAll(validTokenListByUsuario);
    }


    public AuthenticationResponseAdmin inicio(Usuarios request) {
        authenticationManagerAdmin.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Usuarios usuarios = usuariosRepository.findByCorreoUsuario(request.getCorreoUsuario()).orElseThrow();
        String tokenAdmin = jwtServiceAdmin.generatedTokenAdmin(usuarios);
        saveUsuarioToken(tokenAdmin, usuarios);

        return new AuthenticationResponseAdmin(tokenAdmin);
    }

    private void saveUsuarioToken(String token, Usuarios usuarios) {
        TokenAdmin tokenAdmin = new TokenAdmin();
        tokenAdmin.setTokenAdmin(token);
        tokenAdmin.setLoggedOutAdmin(false);
        tokenAdmin.setUsuario(usuarios);
        tokenRepositoryAdmin.save(tokenAdmin);
    }
}
