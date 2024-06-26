package com.api.sitiepro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_servicio")
public class TiposServicios {
    @Id
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
