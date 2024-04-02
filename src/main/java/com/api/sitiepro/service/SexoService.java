package com.api.sitiepro.service;

import com.api.sitiepro.entity.Sexo;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexoService {
    @Autowired
    private SexoRepository sexoRepository;

    public List<Sexo> obtenerTodosLosSexos() {
        return sexoRepository.findAll();
    }

    public Sexo buscarSexoPorId(Long idSexo) throws ResourceNotFoundException {
        return sexoRepository.findById(idSexo)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un sexo para el ID: " + idSexo));
    }

    public Sexo agregarSexo(Sexo sexo) {
        return sexoRepository.save(sexo);
    }
}
