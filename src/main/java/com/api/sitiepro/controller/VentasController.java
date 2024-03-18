package com.api.sitiepro.controller;

import com.api.sitiepro.dto.VentasDTO;
import com.api.sitiepro.entity.Ventas;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    @GetMapping
    public List<Ventas> obtenerTodasLasVentas() {
        return ventasService.obtenerTodasLasVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ventas> buscarVenta(@PathVariable Long id) {
        try {
            Ventas venta = ventasService.buscarVenta(id);
            return ResponseEntity.ok(venta);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Ventas> agregarVenta(@RequestBody VentasDTO ventasDTO) {
        try {
            Ventas nuevaVenta = ventasService.agregarVenta(ventasDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ventas> actualizarVenta(@PathVariable Long id, @RequestBody VentasDTO ventasDTO) {
        try {
            Ventas ventaActualizada = ventasService.actualizarVenta(id, ventasDTO);
            return ResponseEntity.ok(ventaActualizada);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarVenta(@PathVariable Long id) {
        try {
            ventasService.eliminarVenta(id);
            return ResponseEntity.ok(Map.of("eliminado", true));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
