package com.example.javamysql_application_mobile_adoptme.model;

import com.google.gson.annotations.SerializedName;


public class BaseResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("mensaje")
    private String mensaje;

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMensaje() {
        return mensaje;
    }
}