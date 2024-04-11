package com.api.sitiepro.service;

import com.api.sitiepro.entity.AuthenticationResponse;
import com.api.sitiepro.entity.Token;
import com.api.sitiepro.entity.Usuarios;
import com.api.sitiepro.repository.TokenRepository;
import com.api.sitiepro.repository.UsuariosRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final UsuariosRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationService(UsuariosRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager,
                                 TokenRepository tokenRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }

    public AuthenticationResponse register(Usuarios request) {
        Usuarios usuarios = new Usuarios();
        usuarios.setRole(request.getRole());
        usuarios.setNombreUsuario(request.getNombreUsuario());
        usuarios.setCorreoUsuario(request.getCorreoUsuario());
        usuarios.setContrasenaUsuario(passwordEncoder.encode(request.getContrasenaUsuario()));

        usuarios = repository.save(usuarios);

        String jwt = jwtService.generateToken(usuarios);

        revokeAllTokenByUser(usuarios);

        saveUserToken(jwt, usuarios);

        return new AuthenticationResponse(jwt);
    }

    private void revokeAllTokenByUser(Usuarios usuarios) {
        List<Token> validTokenListByUser = tokenRepository.findAllTokenByUser(usuarios.getIdUsuario());
        if (!validTokenListByUser.isEmpty()) {
            validTokenListByUser.forEach(t -> {
                t.setLoggedOut(true);
            });
        }

        tokenRepository.saveAll(validTokenListByUser);
    }

    public AuthenticationResponse authenticate(Usuarios request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreoUsuario(),
                        request.getPassword()
                )
        );

        Usuarios usuarios = repository.findByCorreoUsuario(request.getCorreoUsuario()).orElseThrow();

        // Revoca los tokens anteriores del usuario antes de generar el nuevo token
        revokeAllTokenByUser(usuarios);

        String token = jwtService.generateToken(usuarios);
        saveUserToken(token, usuarios);

        return new AuthenticationResponse(token);
    }

    private void saveUserToken(String jwt, Usuarios usuarios) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUsuarios(usuarios);
        tokenRepository.save(token);
    }

    // Método para actualizar un usuario
    public Usuarios updateUser(Long id, Usuarios request) {
        Usuarios existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existingUser.setRole(request.getRole());
        existingUser.setNombreUsuario(request.getNombreUsuario());
        existingUser.setCorreoUsuario(request.getCorreoUsuario());
        existingUser.setContrasenaUsuario(passwordEncoder.encode(request.getContrasenaUsuario()));
        // Actualizar otros campos si es necesario...

        return repository.save(existingUser);
    }

}
