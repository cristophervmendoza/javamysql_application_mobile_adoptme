package com.example.javamysql_application_mobile_adoptme.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FavoriteResponse {
    private boolean success;
    private String mensaje;
    private List<MascotaEntidad> data;

    public boolean isSuccess() { return success; }
    public String getMensaje() { return mensaje; }
    public List<MascotaEntidad> getData() { return data; }
}

