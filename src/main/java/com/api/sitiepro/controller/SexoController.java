package com.api.sitiepro.controller;

import com.api.sitiepro.entity.Sexo;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.SexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sexo")
public class SexoController {
    @Autowired
    private SexoService sexoService;

    @GetMapping
    public List<Sexo> obtenerTodosLosSexos() {
        return sexoService.obtenerTodosLosSexos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sexo> buscarSexoPorId(@PathVariable Long id) {
        try {
            Sexo sexo = sexoService.buscarSexoPorId(id);
            return ResponseEntity.ok(sexo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Sexo> agregarSexo(@RequestBody Sexo sexo) {
        Sexo nuevoSexo = sexoService.agregarSexo(sexo);
        return ResponseEntity.ok(nuevoSexo);
    }
}
