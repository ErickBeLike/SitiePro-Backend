package com.api.sitiepro.dto;

import java.time.LocalDate;

public class InstalacionesDTO {
    private Long idEmpleadoRegistro;
    private Long idEmpleadoInstalador;
    private Long idVenta;
    private LocalDate fechaInstalacion;

    public Long getIdEmpleadoRegistro() {
        return idEmpleadoRegistro;
    }

    public void setIdEmpleadoRegistro(Long idEmpleadoRegistro) {
        this.idEmpleadoRegistro = idEmpleadoRegistro;
    }

    public Long getIdEmpleadoInstalador() {
        return idEmpleadoInstalador;
    }

    public void setIdEmpleadoInstalador(Long idEmpleadoInstalador) {
        this.idEmpleadoInstalador = idEmpleadoInstalador;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDate getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(LocalDate fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

}
