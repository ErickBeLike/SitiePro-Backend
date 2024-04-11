package com.api.sitiepro.service;

import com.api.sitiepro.entity.Usuarios;
import com.api.sitiepro.repository.UsuariosRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UsuariosRepository repository;

    public UserDetailsServiceImp(UsuariosRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuarios = repository.findByCorreoUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        logger.debug("User roles: " + usuarios.getRole());

        return new org.springframework.security.core.userdetails.User(usuarios.getCorreoUsuario(), usuarios.getPassword(),
                List.of(new SimpleGrantedAuthority(usuarios.getRole().name())));
    }

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);


}
