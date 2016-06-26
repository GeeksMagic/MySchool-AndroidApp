package com.gmt.myschool.api.request;

import com.google.gson.annotations.Expose;

/**
 * Created by user on 6/26/2016.
 */
public class SignInRequest {
    @Expose
    private String username;

    @Expose
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
