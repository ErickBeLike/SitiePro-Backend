package com.api.sitiepro.controller;

import com.api.sitiepro.dto.InstalacionesDTO;
import com.api.sitiepro.entity.Instalaciones;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.InstalacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instalaciones")
public class InstalacionesController {

    @Autowired
    private InstalacionesService instalacionesService;

    @GetMapping
    public List<Instalaciones> obtenerTodasLasInstalaciones() {
        return instalacionesService.obtenerTodasLasInstalaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instalaciones> buscarInstalacionId(@PathVariable Long id) {
        try {
            Instalaciones instalacion = instalacionesService.buscarInstalacionId(id);
            return ResponseEntity.ok(instalacion);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Instalaciones> agregarInstalacion(@RequestBody InstalacionesDTO instalacionesDTO) {
        try {
            Instalaciones nuevaInstalacion = instalacionesService.agregarInstalacion(instalacionesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInstalacion);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instalaciones> actualizarInstalacion(@PathVariable Long id, @RequestBody InstalacionesDTO instalacionesDTO) {
        try {
            Instalaciones instalacionActualizada = instalacionesService.actualizarInstalacion(id, instalacionesDTO);
            return ResponseEntity.ok(instalacionActualizada);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarInstalacion(@PathVariable Long id) {
        try {
            instalacionesService.eliminarInstalacion(id);
            return ResponseEntity.ok(Map.of("eliminado", true));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
