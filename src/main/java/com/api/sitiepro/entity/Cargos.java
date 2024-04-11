package com.api.sitiepro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cargos")
public class Cargos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cargo")
    private Long idCargo;
    @Column(name = "nombre_cargo")
    private String nombreCargo;
    @Column(name = "descripcion_cargo")
    private String descripcionCargo;

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }
}
