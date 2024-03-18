package com.api.sitiepro.controller;

import com.api.sitiepro.dto.UsuariosDTO;
import com.api.sitiepro.entity.Usuarios;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public List<Usuarios> obtenerTodosLosUsuarios() {
        return usuariosService.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> buscarUsuarioId(@PathVariable Long id) {
        try {
            Usuarios usuario = usuariosService.buscarUsuarioId(id);
            return ResponseEntity.ok(usuario);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuarios> agregarUsuario(@RequestBody UsuariosDTO usuariosDTO) {
        try {
            Usuarios nuevoUsuario = usuariosService.agregarUsuario(usuariosDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable Long id, @RequestBody UsuariosDTO usuariosDTO) {
        try {
            Usuarios usuarioActualizado = usuariosService.actualizarUsuario(id, usuariosDTO);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id) {
        try {
            usuariosService.eliminarUsuario(id);
            return ResponseEntity.ok(Map.of("eliminado", true));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
