package com.api.sitiepro.service;

import com.api.sitiepro.dto.EmpleadosDTO;
import com.api.sitiepro.entity.Cargos;
import com.api.sitiepro.entity.Empleados;
import com.api.sitiepro.entity.Sexo;
import com.api.sitiepro.exception.ResourceNotFoundException;
import com.api.sitiepro.repository.CargosRepository;
import com.api.sitiepro.repository.EmpleadosRepository;
import com.api.sitiepro.repository.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadosService {
    @Autowired
    private EmpleadosRepository empleadosRepository;
    @Autowired
    private CargosRepository cargosRepository;
    @Autowired
    private SexoRepository sexoRepository;

     public List<Empleados> obtenerTodosLosEmpleados() {
         return empleadosRepository.findAll();
     }

     public Empleados buscarEmpleadoId(Long idEmpleado) throws ResourceNotFoundException {
         return empleadosRepository.findById(idEmpleado)
                 .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el Id: " + idEmpleado));
     }

     public Empleados agregarEmpleado(EmpleadosDTO empleadosDTO)throws ResourceNotFoundException {
         Cargos cargos = cargosRepository.findById(empleadosDTO.getIdCargo())
                 .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cargo para el ID: " + empleadosDTO.getIdCargo()));

         Sexo sexo = sexoRepository.findById(empleadosDTO.getIdSexo())
                 .orElseThrow(() -> new ResourceNotFoundException("No se encontró un sexo para el ID: " + empleadosDTO.getIdSexo()));


         Empleados empleados = new Empleados();

         empleados.setNombreEmpleado(empleadosDTO.getNombreEmpleado());
         empleados.setApellidoPaEmpleado(empleadosDTO.getApellidoPaEmpleado());
         empleados.setApellidoMaEmpleado(empleadosDTO.getApellidoMaEmpleado());
         empleados.setIdCargo(cargos);
         empleados.setFechaDeNacimiento(empleadosDTO.getFechaDeNacimiento());
         empleados.setIdSexo(sexo);
         empleados.setCorreoEmpleado(empleadosDTO.getCorreoEmpleado());
         empleados.setNumeroEmpleado(empleadosDTO.getNumeroEmpleado());
         empleados.setDireccionEmpleado(empleadosDTO.getDireccionEmpleado());

         empleadosRepository.save(empleados);
         return empleados;
     }

    public Empleados actualizarEmpleado(Long idEmpleado, EmpleadosDTO empleadosDTO) throws ResourceNotFoundException {
        Empleados empleados = empleadosRepository.findById(idEmpleado)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + idEmpleado));

        Cargos cargo = cargosRepository.findById(empleadosDTO.getIdCargo())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un cargo para el ID: " + empleadosDTO.getIdCargo()));

        Sexo sexo = sexoRepository.findById(empleadosDTO.getIdSexo())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un sexo para el ID: " + empleadosDTO.getIdSexo()));

        empleados.setNombreEmpleado(empleadosDTO.getNombreEmpleado());
        empleados.setApellidoPaEmpleado(empleadosDTO.getApellidoPaEmpleado());
        empleados.setApellidoMaEmpleado(empleadosDTO.getApellidoMaEmpleado());
        empleados.setIdCargo(cargo);
        empleados.setFechaDeNacimiento(empleadosDTO.getFechaDeNacimiento());
        empleados.setIdSexo(sexo);
        empleados.setCorreoEmpleado(empleadosDTO.getCorreoEmpleado());
        empleados.setNumeroEmpleado(empleadosDTO.getNumeroEmpleado());
        empleados.setDireccionEmpleado(empleadosDTO.getDireccionEmpleado());

        return empleadosRepository.save(empleados);
    }

     public void eliminarEmpleado(Long idEmpleado) throws ResourceNotFoundException {
         Empleados empleados = empleadosRepository.findById(idEmpleado)
                 .orElseThrow(() -> new ResourceNotFoundException("No se encontró un empleado para el ID: " + idEmpleado));

         empleadosRepository.delete(empleados);
     }
}



