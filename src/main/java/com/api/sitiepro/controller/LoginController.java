package com.api.sitiepro.controller;

import com.api.sitiepro.entity.Roles;
import com.api.sitiepro.entity.Usuarios;
import com.api.sitiepro.repository.RolesRepository;
import com.api.sitiepro.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        String correoUsuario = request.get("correo");
        String contrasena = request.get("contrasena");

        Usuarios usuario = usuariosRepository.findByCorreoUsuario(correoUsuario);
        if (usuario != null && usuario.getContrasenaUsuario().equals(contrasena)) {
            Roles rol = usuario.getIdRol();
            if (rol != null) {
                Map<String, String> response = new HashMap<>();
                response.put("mensaje", "Login exitoso");
                response.put("nombreUsuario", usuario.getNombreUsuario());
                response.put("rol", rol.getNombreRol());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse("El usuario no tiene un rol asignado"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse("Credenciales inválidas"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Sesión cerrada correctamente");
        return ResponseEntity.ok(response);
    }

    private Map<String, String> errorResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", message);
        return response;
    }
}
