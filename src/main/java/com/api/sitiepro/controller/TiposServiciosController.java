package com.api.sitiepro.controller;

import com.api.sitiepro.entity.TiposServicios;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.TiposServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tipos-servicios")
public class TiposServiciosController {

    @Autowired
    private TiposServiciosService tiposServiciosService;

    @GetMapping
    public List<TiposServicios> obtenerTodosLosTiposServicios() {
        return tiposServiciosService.obtenerTodosLosTiposServicios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiposServicios> buscarTipoServicioPorId(@PathVariable Long id) {
        try {
            TiposServicios tipoServicio = tiposServiciosService.buscarTipoServicioPorId(id);
            return ResponseEntity.ok(tipoServicio);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TiposServicios> agregarTipoServicio(@RequestBody TiposServicios tipoServicio) {
        TiposServicios nuevoTipoServicio = tiposServiciosService.agregarTipoServicio(tipoServicio);
        return ResponseEntity.ok(nuevoTipoServicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TiposServicios> actualizarTipoServicio(@PathVariable Long id, @RequestBody TiposServicios datosTipoServicio) {
        try {
            TiposServicios tipoServicioActualizado = tiposServiciosService.actualizarTipoServicio(id, datosTipoServicio);
            return ResponseEntity.ok(tipoServicioActualizado);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarTipoServicio(@PathVariable Long id) {
        try {
            tiposServiciosService.eliminarTipoServicio(id);
            return ResponseEntity.ok().body(Map.of("eliminado", true));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
