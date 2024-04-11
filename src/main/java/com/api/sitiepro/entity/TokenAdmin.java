package com.api.sitiepro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "token_admin")
public class TokenAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_token")
    private Long idToken;
    @Column(name = "token_admin")
    private String tokenAdmin;

    @Column(name = "is_logged_out_admin")
    private boolean loggedOutAdmin;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    public Long getIdToken() {
        return idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }

    public String getTokenAdmin() {
        return tokenAdmin;
    }

    public void setTokenAdmin(String tokenAdmin) {
        this.tokenAdmin = tokenAdmin;
    }

    public boolean isLoggedOutAdmin() {
        return loggedOutAdmin;
    }

    public void setLoggedOutAdmin(boolean loggedOutAdmin) {
        this.loggedOutAdmin = loggedOutAdmin;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
