package com.example.demo.jwt;

public class JwtResponse {

    private String token;
    private String prefix;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}