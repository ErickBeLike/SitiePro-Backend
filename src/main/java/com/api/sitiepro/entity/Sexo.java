package com.api.sitiepro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sexo")
public class Sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sexo")
    private Long idSexo;
    @Column(name = "nombre_sexo")
    private String nombreSexo;

    public Long getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Long idSexo) {
        this.idSexo = idSexo;
    }

    public String getNombreSexo() {
        return nombreSexo;
    }

    public void setNombreSexo(String nombreSexo) {
        this.nombreSexo = nombreSexo;
    }
}
