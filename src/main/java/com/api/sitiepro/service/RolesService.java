package com.api.sitiepro.service;

import com.api.sitiepro.entity.Roles;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public List<Roles> obtenerTodosLosRoles() {
        return rolesRepository.findAll();
    }

    public Roles buscarRolPorId(Long idRol) throws ResourceNotFoundException {
        return rolesRepository.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol para el Id: " + idRol));
    }

    public Roles agregarRol(Roles rol) {
        return rolesRepository.save(rol);
    }

    public Roles actualizarRol(Long idRol, Roles rolDetails) throws ResourceNotFoundException {
        Roles rol = rolesRepository.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol para el Id: " + idRol));

        rol.setNombreRol(rolDetails.getNombreRol());

        return rolesRepository.save(rol);
    }

    public void eliminarRol(Long idRol) throws ResourceNotFoundException {
        Roles rol = rolesRepository.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol para el Id: " + idRol));

        rolesRepository.delete(rol);
    }
}
