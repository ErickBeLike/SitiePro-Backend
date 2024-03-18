package com.api.sitiepro.controller;

import com.api.sitiepro.dto.ServiciosDTO;
import com.api.sitiepro.entity.Clientes;
import com.api.sitiepro.entity.Servicios;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @GetMapping
    public List<Clientes> obtenerTodosLosClientes() {
        return clientesService.obtenerTodosLosClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> buscarClienteId(@PathVariable Long id) {
        try {
            Clientes cliente = clientesService.buscarClienteId(id);
            return ResponseEntity.ok(cliente);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Clientes> agregarCliente(@RequestBody Clientes cliente) {
        Clientes nuevoCliente = clientesService.agregarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> actualizarCliente(@PathVariable Long id, @RequestBody Clientes datosCliente)
            throws ResourceNotFoundException {
        Clientes updatedCliente = clientesService.actualizarCliente(id, datosCliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> eliminarCliente(@PathVariable Long id) throws ResourceNotFoundException {
        clientesService.eliminarCliente(id);
        Map<String, Boolean> response = Map.of("eliminado", true);
        return response;
    }
}
