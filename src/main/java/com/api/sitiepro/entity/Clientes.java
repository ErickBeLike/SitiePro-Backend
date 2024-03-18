package com.api.sitiepro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "nombre_s", nullable = false)
    private String nombreCliente;
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaCliente;
    @Column(name = "apellido_materno")
    private String apellidoMaCliente;
    @Column(name = "direccion", nullable = false)
    private String direccionCliente;
    @Column(name = "correo")
    private String correoCliente;
    @Column(name = "telefono")
    private String telefonoCliente;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoPaCliente() {
        return apellidoPaCliente;
    }

    public void setApellidoPaCliente(String apellidoPaCliente) {
        this.apellidoPaCliente = apellidoPaCliente;
    }

    public String getApellidoMaCliente() {
        return apellidoMaCliente;
    }

    public void setApellidoMaCliente(String apellidoMaCliente) {
        this.apellidoMaCliente = apellidoMaCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
}
