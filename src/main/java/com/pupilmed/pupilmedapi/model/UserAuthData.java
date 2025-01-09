package com.pupilmed.pupilmedapi.model;

import jakarta.persistence.Transient;

public class UserAuthData extends User{

    @Transient
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
