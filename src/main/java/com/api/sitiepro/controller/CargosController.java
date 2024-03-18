package com.api.sitiepro.controller;

import com.api.sitiepro.entity.Cargos;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.CargosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cargos")
public class CargosController {

    @Autowired
    private CargosService cargosService;

    @GetMapping
    public List<Cargos> obtenerTodosLosCargos() {
        return cargosService.obtenerTodosLosCargos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargos> buscarCargoPorId(@PathVariable Long id) {
        try {
            Cargos cargo = cargosService.buscarCargoPorId(id);
            return ResponseEntity.ok(cargo);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cargos> agregarCargo(@RequestBody Cargos cargo) {
        Cargos nuevoCargo = cargosService.agregarCargo(cargo);
        return ResponseEntity.ok(nuevoCargo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargos> actualizarCargo(@PathVariable Long id, @RequestBody Cargos datosCargo) {
        try {
            Cargos cargoActualizado = cargosService.actualizarCargo(id, datosCargo);
            return ResponseEntity.ok(cargoActualizado);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> eliminarCargo(@PathVariable Long id) {
        try {
            cargosService.eliminarCargo(id);
            return Map.of("eliminado", true);
        } catch (ResourceNotFoundException e) {
            return Map.of("eliminado", false);
        }
    }
}
