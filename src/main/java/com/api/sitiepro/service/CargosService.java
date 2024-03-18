package com.api.sitiepro.service;

import com.api.sitiepro.entity.Cargos;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.CargosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargosService {

    @Autowired
    private CargosRepository cargosRepository;

    public List<Cargos> obtenerTodosLosCargos() {
        return cargosRepository.findAll();
    }

    public Cargos buscarCargoPorId(Long idCargo) throws ResourceNotFoundException {
        return cargosRepository.findById(idCargo)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cargo para el ID: " + idCargo));
    }

    public Cargos agregarCargo(Cargos cargo) {
        return cargosRepository.save(cargo);
    }

    public Cargos actualizarCargo(Long idCargo, Cargos datosCargo) throws ResourceNotFoundException {
        Cargos cargo = cargosRepository.findById(idCargo)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cargo para el ID: " + idCargo));

        cargo.setNombreCargo(datosCargo.getNombreCargo());
        cargo.setDescripcionCargo(datosCargo.getDescripcionCargo());

        return cargosRepository.save(cargo);
    }

    public void eliminarCargo(Long idCargo) throws ResourceNotFoundException {
        Cargos cargo = cargosRepository.findById(idCargo)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cargo para el ID: " + idCargo));

        cargosRepository.delete(cargo);
    }
}
