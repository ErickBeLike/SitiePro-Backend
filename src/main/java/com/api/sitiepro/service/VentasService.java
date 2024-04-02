package com.api.sitiepro.service;

import com.api.sitiepro.dto.VentasDTO;
import com.api.sitiepro.entity.Clientes;
import com.api.sitiepro.entity.Empleados;
import com.api.sitiepro.entity.Servicios;
import com.api.sitiepro.entity.Ventas;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.ClientesRepository;
import com.api.sitiepro.repository.EmpleadosRepository;
import com.api.sitiepro.repository.ServiciosRepository;
import com.api.sitiepro.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentasService {

    @Autowired
    private VentasRepository ventasRepository;
    @Autowired
    private EmpleadosRepository empleadosRepository;
    @Autowired
    private ServiciosRepository serviciosRepository;
    @Autowired
    private ClientesRepository clientesRepository;

    public List<Ventas> obtenerTodasLasVentas() {
        return ventasRepository.findAll();
    }

    public Ventas buscarVentaId(Long idVenta) throws ResourceNotFoundException {
        return ventasRepository.findById(idVenta)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una venta para el Id: " + idVenta));
    }

    public Ventas agregarVenta(VentasDTO ventasDTO) throws ResourceNotFoundException {
        Empleados empleados = empleadosRepository.findById(ventasDTO.getIdEmpleado())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + ventasDTO.getIdEmpleado()));

        Servicios servicios = serviciosRepository.findById(ventasDTO.getIdServicio())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio para el ID: " + ventasDTO.getIdServicio()));

        Clientes clientes = clientesRepository.findById(ventasDTO.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el ID: " + ventasDTO.getIdCliente()));

        Ventas ventas = new Ventas();

        ventas.setIdEmpleado(empleados);
        ventas.setFechaVenta(LocalDateTime.now());
        ventas.setIdServicio(servicios);
        ventas.setTotal(servicios.getPrecioServicio());
        ventas.setIdCliente(clientes);

        ventasRepository.save(ventas);
        return ventas;
    }

    public Ventas actualizarVenta(Long idVenta, VentasDTO ventasDTO) throws  ResourceNotFoundException {
        Ventas ventas = ventasRepository.findById(idVenta)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una venta para el ID: " + idVenta));

        Empleados empleados = empleadosRepository.findById(ventasDTO.getIdEmpleado())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + ventasDTO.getIdEmpleado()));

        Servicios servicios = serviciosRepository.findById(ventasDTO.getIdServicio())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio para el ID: " + ventasDTO.getIdServicio()));

        Clientes clientes = clientesRepository.findById(ventasDTO.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cliente para el ID: " + ventasDTO.getIdCliente()));

        ventas.setIdEmpleado(empleados);
        ventas.setFechaVenta(LocalDateTime.now());
        ventas.setIdServicio(servicios);
        ventas.setTotal(servicios.getPrecioServicio());
        ventas.setIdCliente(clientes);

        return ventasRepository.save(ventas);
    }

    public void eliminarVenta(Long idVenta) throws ResourceNotFoundException {
        Ventas ventas = ventasRepository.findById(idVenta)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + idVenta));

        ventasRepository.delete(ventas);
    }
}
