package com.api.sitiepro.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "instalaciones")
public class Instalaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_instalacion")
    private Long idInstalacion;
    @ManyToOne
    @JoinColumn(name = "id_empleado_registro")
    private Empleados idEmpleadoRegistro;
    @ManyToOne
    @JoinColumn(name = "id_empleado_instalador")
    private Empleados idEmpleadoInstalador;
    @OneToOne
    @JoinColumn(name = "id_venta")
    private Ventas idVenta;
    @Column(name = "fecha_instalacion")
    private LocalDate fechaInstalacion;
    @Column(name = "direccion")
    private String direccion;

    public Long getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(Long idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public Empleados getIdEmpleadoRegistro() {
        return idEmpleadoRegistro;
    }

    public void setIdEmpleadoRegistro(Empleados idEmpleadoRegistro) {
        this.idEmpleadoRegistro = idEmpleadoRegistro;
    }

    public Empleados getIdEmpleadoInstalador() {
        return idEmpleadoInstalador;
    }

    public void setIdEmpleadoInstalador(Empleados idEmpleadoInstalador) {
        this.idEmpleadoInstalador = idEmpleadoInstalador;
    }

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDate getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(LocalDate fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
