package com.api.sitiepro.controller;

import com.api.sitiepro.entity.Roles;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping
    public List<Roles> obtenerTodosLosRoles() {
        return rolesService.obtenerTodosLosRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> buscarRolPorId(@PathVariable Long id) {
        try {
            Roles rol = rolesService.buscarRolPorId(id);
            return ResponseEntity.ok(rol);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Roles> agregarRol(@RequestBody Roles rol) {
        Roles nuevoRol = rolesService.agregarRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Roles> actualizarRol(@PathVariable Long id, @RequestBody Roles rolDetails) {
        try {
            Roles rolActualizado = rolesService.actualizarRol(id, rolDetails);
            return ResponseEntity.ok(rolActualizado);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarRol(@PathVariable Long id) {
        try {
            rolesService.eliminarRol(id);
            return ResponseEntity.ok(Map.of("eliminado", true));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
