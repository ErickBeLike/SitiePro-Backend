package com.api.sitiepro.service;

import com.api.sitiepro.dto.UsuariosDTO;
import com.api.sitiepro.entity.Roles;
import com.api.sitiepro.entity.Usuarios;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.RolesRepository;
import com.api.sitiepro.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private RolesRepository rolesRepository;

    public List<Usuarios> obtenerTodosLosUsuarios() {
        return usuariosRepository.findAll();
    }

    public Usuarios buscarUsuarioId(Long idUsuario) throws ResourceNotFoundException {
        return usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario para el Id: " + idUsuario));
    }

    public Usuarios agregarUsuario(UsuariosDTO usuariosDTO) throws ResourceNotFoundException {
        Roles roles = rolesRepository.findById(usuariosDTO.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol para el ID: " + usuariosDTO.getIdRol()));

        Usuarios usuarios = new Usuarios();

        usuarios.setIdRol(roles);
        usuarios.setNombreUsuario(usuariosDTO.getNombreUsuario());
        usuarios.setCorreoUsuario(usuariosDTO.getCorreoUsuario());
        usuarios.setContrasenaUsuario(usuariosDTO.getContrasenaUsuario());

        usuariosRepository.save(usuarios);


        return usuarios;
    }

    public Usuarios actualizarUsuario(Long idUsuario, UsuariosDTO usuariosDTO) throws ResourceNotFoundException {
        Usuarios usuarios = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario para el ID: " + idUsuario));

        Roles roles = rolesRepository.findById(usuariosDTO.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontrpo un rol para el ID: " + usuariosDTO.getIdRol()));

        usuarios.setIdRol(roles);
        usuarios.setNombreUsuario(usuariosDTO.getNombreUsuario());
        usuarios.setCorreoUsuario(usuariosDTO.getCorreoUsuario());
        usuarios.setContrasenaUsuario(usuariosDTO.getContrasenaUsuario());

        return usuariosRepository.save(usuarios);
    }

    public void eliminarUsuario(Long idUsuario) throws ResourceNotFoundException {
        Usuarios usuarios = usuariosRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario para el ID: " + idUsuario));

        usuariosRepository.delete(usuarios);
    }
}
