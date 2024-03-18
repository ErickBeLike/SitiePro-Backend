package com.api.sitiepro.service;

import com.api.sitiepro.dto.InstalacionesDTO;
import com.api.sitiepro.entity.Clientes;
import com.api.sitiepro.entity.Empleados;
import com.api.sitiepro.entity.Instalaciones;
import com.api.sitiepro.entity.Ventas;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.ClientesRepository;
import com.api.sitiepro.repository.EmpleadosRepository;
import com.api.sitiepro.repository.InstalacionesRepository;
import com.api.sitiepro.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstalacionesService {
    @Autowired
    private InstalacionesRepository instalacionesRepository;
    @Autowired
    private EmpleadosRepository empleadosRepository;
    @Autowired
    private VentasRepository ventasRepository;
    @Autowired
    private ClientesRepository clientesRepository;

    public List<Instalaciones> obtenerTodasLasInstalaciones() {
        return instalacionesRepository.findAll();
    }

    public Instalaciones buscarInstalacionId(Long idInstalacion) throws ResourceNotFoundException {
        return instalacionesRepository.findById(idInstalacion)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una instalación para el Id: " + idInstalacion));
    }

    public Instalaciones agregarInstalacion(InstalacionesDTO instalacionesDTO) throws ResourceNotFoundException {
        Empleados empleadoRegistro = empleadosRepository.findById(instalacionesDTO.getIdEmpleadoRegistro())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + instalacionesDTO.getIdEmpleadoRegistro()));

        Empleados empleadosInstalacion = empleadosRepository.findById(instalacionesDTO.getIdEmpleadoInstalador())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + instalacionesDTO.getIdEmpleadoInstalador()));

        Ventas ventas = ventasRepository.findById(instalacionesDTO.getIdVenta())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una venta para el ID: " + instalacionesDTO.getIdVenta()));

        Clientes cliente = ventas.getIdCliente();

        Instalaciones instalaciones = new Instalaciones();

        instalaciones.setIdEmpleadoRegistro(empleadoRegistro);
        instalaciones.setIdEmpleadoInstalador(empleadosInstalacion);
        instalaciones.setIdVenta(ventas);
        instalaciones.setFechaInstalacion(instalacionesDTO.getFechaInstalacion());
        instalaciones.setDireccion(cliente.getDireccionCliente());

        instalacionesRepository.save(instalaciones);
        return instalaciones;

    }

    public Instalaciones actualizarInstalacion(Long idInstalacion, InstalacionesDTO instalacionesDTO) throws ResourceNotFoundException {
        Instalaciones instalaciones = instalacionesRepository.findById(idInstalacion)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una instalación para el ID: " + idInstalacion));

        Empleados empleadoRegistro = empleadosRepository.findById(instalacionesDTO.getIdEmpleadoRegistro())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + instalacionesDTO.getIdEmpleadoRegistro()));

        Empleados empleadosInstalacion = empleadosRepository.findById(instalacionesDTO.getIdEmpleadoInstalador())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + instalacionesDTO.getIdEmpleadoInstalador()));

        Ventas ventas = ventasRepository.findById(instalacionesDTO.getIdVenta())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró una venta para el ID: " + instalacionesDTO.getIdVenta()));

        Clientes cliente = ventas.getIdCliente();

        instalaciones.setIdEmpleadoRegistro(empleadoRegistro);
        instalaciones.setIdEmpleadoInstalador(empleadosInstalacion);
        instalaciones.setIdVenta(ventas);
        instalaciones.setFechaInstalacion(instalacionesDTO.getFechaInstalacion());
        instalaciones.setDireccion(cliente.getDireccionCliente());

        return instalacionesRepository.save(instalaciones);
    }

    public void eliminarInstalacion(Long idInstalacion) throws ResourceNotFoundException {
        Instalaciones instalaciones = instalacionesRepository.findById(idInstalacion)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + idInstalacion));

        instalacionesRepository.delete(instalaciones);
    }
}
