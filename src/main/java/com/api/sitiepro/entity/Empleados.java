package com.api.sitiepro.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "empleados")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @Column(name = "nombre_s", nullable = false)
    private String nombreEmpleado;
    @Column(name = "apellido_paterno", nullable = false)
    private  String apellidoPaEmpleado;
    @Column(name = "apellido_materno")
    private String apellidoMaEmpleado;
    @ManyToOne
    @JoinColumn(name = "cargo", nullable = false)
    private Cargos idCargo;
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaDeNacimiento;
    @ManyToOne
    @JoinColumn(name = "sexo", nullable = false)
    private Sexo idSexo;
    @Column(name = "correo")
    private String correoEmpleado;
    @Column(name = "telefono")
    private String numeroEmpleado;
    @Column(name = "direccion", nullable = false)
    private String direccionEmpleado;

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

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

    public Cargos getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargos idCargo) {
        this.idCargo = idCargo;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Sexo getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Sexo idSexo) {
        this.idSexo = idSexo;
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
