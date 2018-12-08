package com.junying.Dtos;

import java.io.Serializable;

public class Credentials implements Serializable {
    String email;
    String password;

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Credentials() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
