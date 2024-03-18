package com.api.sitiepro.controller;

import com.api.sitiepro.dto.ServiciosDTO;
import com.api.sitiepro.entity.Servicios;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.ServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/servicios")
public class ServiciosController {

    @Autowired
    private ServiciosService serviciosService;

    @GetMapping
    public List<Servicios> obtenerTodosLosServicios() {
        return serviciosService.obtenerTodosLosServicios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicios> buscarServicioId(@PathVariable Long id) throws ResourceNotFoundException {
        Servicios servicios = serviciosService.buscarServicioId(id);
        return ResponseEntity.ok().body(servicios);
    }

    @PostMapping
    public Servicios agregarServicio(@RequestBody ServiciosDTO serviciosDTO) throws ResourceNotFoundException {
        return serviciosService.agregarServicio(serviciosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicios> actualizarServicio(@PathVariable Long id, @RequestBody ServiciosDTO serviciosDTO)
            throws ResourceNotFoundException {
        Servicios updatedServicio = serviciosService.actualizarServicio(id, serviciosDTO);
        return ResponseEntity.ok(updatedServicio);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> eliminarEmpleado(@PathVariable Long id) throws ResourceNotFoundException {
        serviciosService.eliminarServicio(id);
        Map<String, Boolean> response = Map.of("eliminado", true);
        return response;
    }
}
