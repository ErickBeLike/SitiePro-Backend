package com.api.sitiepro.dto;

import com.api.sitiepro.entity.Empleados;

import java.time.LocalDateTime;

public class VentasDTO {
    private Long idEmpleado;
    private Long idServicio;
    private Long idCliente;


    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
