package com.api.sitiepro.controller;

import com.api.sitiepro.entity.AuthenticationResponse;
import com.api.sitiepro.entity.Usuarios;
import com.api.sitiepro.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Usuarios request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Usuarios request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    // Método para actualizar un usuario
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuarios> updateUser(
            @PathVariable("id") Long id,
            @RequestBody Usuarios request
    ){
        Usuarios updatedUser = authService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }


}
