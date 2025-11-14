package com.example.javamysql_application_mobile_adoptme.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CatalogoRespuesta {

    @SerializedName("success")
    private boolean success;

    @SerializedName("mensaje")
    private String mensaje;

    @SerializedName("mascotas")
    private List<MascotaEntidad> listaMascotas;

    // Getters
    public boolean isSuccess() { return success; }
    public String getMensaje() { return mensaje; }
    public List<MascotaEntidad> getListaMascotas() { return listaMascotas; }
}