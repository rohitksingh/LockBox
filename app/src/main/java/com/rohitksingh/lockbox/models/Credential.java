package com.rohitksingh.lockbox.models;

import java.io.Serializable;

public class Credential implements Serializable {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
