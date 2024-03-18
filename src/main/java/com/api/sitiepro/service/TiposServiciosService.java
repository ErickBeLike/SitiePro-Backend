package com.api.sitiepro.service;

import com.api.sitiepro.entity.TiposServicios;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.TiposServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiposServiciosService {

    @Autowired
    private TiposServiciosRepository tiposServiciosRepository;

    public List<TiposServicios> obtenerTodosLosTiposServicios() {
        return tiposServiciosRepository.findAll();
    }

    public TiposServicios buscarTipoServicioPorId(Long idTipoServicio) throws ResourceNotFoundException {
        return tiposServiciosRepository.findById(idTipoServicio)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un tipo de servicio para el ID: " + idTipoServicio));
    }

    public TiposServicios agregarTipoServicio(TiposServicios tipoServicio) {
        return tiposServiciosRepository.save(tipoServicio);
    }

    public TiposServicios actualizarTipoServicio(Long idTipoServicio, TiposServicios datosTipoServicio) throws ResourceNotFoundException {
        TiposServicios tipoServicio = tiposServiciosRepository.findById(idTipoServicio)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un tipo de servicio para el ID: " + idTipoServicio));

        tipoServicio.setTipoServicio(datosTipoServicio.getTipoServicio());

        return tiposServiciosRepository.save(tipoServicio);
    }

    public void eliminarTipoServicio(Long idTipoServicio) throws ResourceNotFoundException {
        TiposServicios tipoServicio = tiposServiciosRepository.findById(idTipoServicio)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un tipo de servicio para el ID: " + idTipoServicio));

        tiposServiciosRepository.delete(tipoServicio);
    }
}
