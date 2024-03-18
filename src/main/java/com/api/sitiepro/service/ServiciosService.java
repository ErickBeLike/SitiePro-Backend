package com.api.sitiepro.service;

import com.api.sitiepro.dto.ServiciosDTO;
import com.api.sitiepro.entity.Servicios;
import com.api.sitiepro.entity.TiposServicios;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.ServiciosRepository;
import com.api.sitiepro.repository.TiposServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiciosService {
    @Autowired
    private ServiciosRepository serviciosRepository;
    @Autowired
    private TiposServiciosRepository tiposServiciosRepository;

    public List<Servicios> obtenerTodosLosServicios() {
        return serviciosRepository.findAll();
    }

    public Servicios buscarServicioId(Long idServicio) throws ResourceNotFoundException {
        return serviciosRepository.findById(idServicio)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el Id: " + idServicio));
    }

    public Servicios agregarServicio(ServiciosDTO serviciosDTO) throws ResourceNotFoundException {
        TiposServicios tiposServicios = tiposServiciosRepository.findById(serviciosDTO.getIdTipoServicio())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un tipo servicio para el ID: " + serviciosDTO.getIdTipoServicio()));

        Servicios servicios = new Servicios();

        servicios.setNombreServicio(serviciosDTO.getNombreServicio());
        servicios.setIdTipoServicio(tiposServicios);
        servicios.setPrecioServicio(serviciosDTO.getPrecioServicio());
        servicios.setDescripcion(serviciosDTO.getDescripcion());

        serviciosRepository.save(servicios);
        return servicios;
    }

    public Servicios actualizarServicio(Long idServicio, ServiciosDTO serviciosDTO) throws ResourceNotFoundException {
        Servicios servicios = serviciosRepository.findById(idServicio)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio para el ID: " + idServicio));

        TiposServicios tiposServicios = tiposServiciosRepository.findById(serviciosDTO.getIdTipoServicio())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un tipo servicio para el ID: " + serviciosDTO.getIdTipoServicio()));

        servicios.setNombreServicio(serviciosDTO.getNombreServicio());
        servicios.setIdTipoServicio(tiposServicios);
        servicios.setPrecioServicio(serviciosDTO.getPrecioServicio());
        servicios.setDescripcion(serviciosDTO.getDescripcion());

        return serviciosRepository.save(servicios);
    }

    public void eliminarServicio(Long idServicio) throws ResourceNotFoundException {
        Servicios servicios = serviciosRepository.findById(idServicio)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un servicio para el ID: " + idServicio));

        serviciosRepository.delete(servicios);
    }
}
