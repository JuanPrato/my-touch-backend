package com.juanprato.mytouch.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juanprato.mytouch.model.User;

import java.util.Date;

public class Token {

    @JsonProperty("token")
    private String token;

    @JsonProperty("expiration")
    private Date expiration;

    public Token(String token, Date expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public Token() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
