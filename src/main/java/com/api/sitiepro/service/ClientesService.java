package com.api.sitiepro.service;

import com.api.sitiepro.entity.Clientes;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientesService {
    @Autowired
    private ClientesRepository clientesRepository;

    public List<Clientes> obtenerTodosLosClientes() {
        return clientesRepository.findAll();
    }

    public Clientes buscarClienteId(Long idCliente) throws ResourceNotFoundException {
        return clientesRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el ID: " + idCliente));
    }

    public Clientes agregarCliente(Clientes clientes) {
        return clientesRepository.save(clientes);
    }

    public Clientes actualizarCliente(Long idCliente, Clientes datosCliente) throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el ID: " + idCliente));

        clientes.setNombreCliente(datosCliente.getNombreCliente());
        clientes.setApellidoPaCliente(datosCliente.getApellidoPaCliente());
        clientes.setApellidoMaCliente(datosCliente.getApellidoMaCliente());
        clientes.setDireccionCliente(datosCliente.getDireccionCliente());
        clientes.setCorreoCliente(datosCliente.getCorreoCliente());
        clientes.setTelefonoCliente(datosCliente.getTelefonoCliente());

        return clientesRepository.save(clientes);
    }

    public Map<String, Boolean> eliminarCliente(Long idCliente) throws ResourceNotFoundException {
        Clientes clientes = clientesRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el ID: " + idCliente));

        clientesRepository.delete(clientes);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
