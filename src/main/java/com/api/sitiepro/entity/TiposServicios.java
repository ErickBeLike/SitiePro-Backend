package com.api.sitiepro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_servicio")
public class TiposServicios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tipo_servicio")
    private Long idTipoServicio;
    @Column(name = "tipo_servicio")
    private String tipoServicio;

    public Long getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(Long idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
}
