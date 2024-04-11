package com.api.sitiepro.controller;

import com.api.sitiepro.entity.AuthenticationResponseAdmin;
import com.api.sitiepro.entity.Usuarios;
import com.api.sitiepro.service.AuthenticationServiceAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationControllerAdmin {
    private final AuthenticationServiceAdmin authServiceAdmin;

    public AuthenticationControllerAdmin(AuthenticationServiceAdmin authServiceAdmin) {
        this.authServiceAdmin = authServiceAdmin;
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthenticationResponseAdmin> registro(
            @RequestBody Usuarios request
            ) {
        return ResponseEntity.ok(authServiceAdmin.registro(request));
    }

    @PostMapping("/inicio")
    public ResponseEntity<AuthenticationResponseAdmin> inicio(
            @RequestBody Usuarios usuarios
    ) {
        return ResponseEntity.ok(authServiceAdmin.inicio(usuarios));
    }

}
