package com.api.sitiepro.controller;

import com.api.sitiepro.dto.EmpleadosDTO;
import com.api.sitiepro.entity.Empleados;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.EmpleadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadosController {

    @Autowired
    private EmpleadosService empleadosService;

    @GetMapping
    public List<Empleados> obtenerTodosLosEmpleados() {
        return empleadosService.obtenerTodosLosEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleados> buscarEmpleadoId(@PathVariable Long id) throws ResourceNotFoundException {
        Empleados empleado = empleadosService.buscarEmpleadoId(id);
        return ResponseEntity.ok().body(empleado);
    }

    @PostMapping
    public Empleados agregarEmpleado(@RequestBody EmpleadosDTO empleadosDTO) throws ResourceNotFoundException {
        return empleadosService.agregarEmpleado(empleadosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleados> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadosDTO empleadosDTO)
            throws ResourceNotFoundException {
        Empleados updatedEmpleado = empleadosService.actualizarEmpleado(id, empleadosDTO);
        return ResponseEntity.ok(updatedEmpleado);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> eliminarEmpleado(@PathVariable Long id) throws ResourceNotFoundException {
        empleadosService.eliminarEmpleado(id);
        Map<String, Boolean> response = Map.of("eliminado", true);
        return response;
    }
}
