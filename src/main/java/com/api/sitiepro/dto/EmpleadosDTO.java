package com.api.sitiepro.dto;

import java.time.LocalDate;

public class EmpleadosDTO {
    private String nombreEmpleado;
    private String apellidoPaEmpleado;
    private String apellidoMaEmpleado;
    private Long idCargo;
    private LocalDate fechaDeNacimiento;
    private String sexo;
    private String correoEmpleado;
    private String numeroEmpleado;
    private String direccionEmpleado;

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoPaEmpleado() {
        return apellidoPaEmpleado;
    }

    public void setApellidoPaEmpleado(String apellidoPaEmpleado) {
        this.apellidoPaEmpleado = apellidoPaEmpleado;
    }

    public String getApellidoMaEmpleado() {
        return apellidoMaEmpleado;
    }

    public void setApellidoMaEmpleado(String apellidoMaEmpleado) {
        this.apellidoMaEmpleado = apellidoMaEmpleado;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreoEmpleado() {
        return correoEmpleado;
    }

    public void setCorreoEmpleado(String correoEmpleado) {
        this.correoEmpleado = correoEmpleado;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }
}
