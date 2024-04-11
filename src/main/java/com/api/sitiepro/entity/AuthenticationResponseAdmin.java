package com.api.sitiepro.entity;

public class AuthenticationResponseAdmin {
    private String tokenAdmin;

    public AuthenticationResponseAdmin(String tokenAdmin) {
        this.tokenAdmin = tokenAdmin;
    }

    public String getTokenAdmin() {
        return tokenAdmin;
    }
}
