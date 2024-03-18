package com.api.sitiepro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_servicio")
    private Long idServicio;
    @Column(name = "nombre_servicio", nullable = false)
    private String nombreServicio;
    @ManyToOne
    @JoinColumn(name = "tipo_servicio", nullable = false)
    private TiposServicios idTipoServicio;
    @Column(name = "precio_servicio", nullable = false)
    private Double precioServicio;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public TiposServicios getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(TiposServicios idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public Double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(Double precioServicio) {
        this.precioServicio = precioServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
