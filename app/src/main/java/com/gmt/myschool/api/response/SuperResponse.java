package com.gmt.myschool.api.response;

import com.google.gson.annotations.Expose;

public class SuperResponse {

    @Expose
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
