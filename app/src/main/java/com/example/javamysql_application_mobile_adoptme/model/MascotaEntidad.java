package com.example.javamysql_application_mobile_adoptme.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MascotaEntidad implements Serializable {


    private static final long serialVersionUID = 1L;

    @SerializedName("id_mascota")
    private int idMascota;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("raza")
    private String raza;

    @SerializedName("especie")
    private String especie;

    @SerializedName("edad")
    private String edad;

    @SerializedName("tamano")
    private String tamano;

    @SerializedName("sexo")
    private String sexo;
    @SerializedName("estado_salud")
    private String estadoSalud;

    @SerializedName("ubicacion")
    private String ubicacion;

    @SerializedName("estado")
    private String estado;

    @SerializedName("refugio_nombre")
    private String nombreRefugio;

    @SerializedName("foto_principal")
    private String fotoPrincipalBase64;

    @SerializedName("historia")
    private String historia;
    @SerializedName("requisitos_adopcion")
    private String requisitosAdopcion;


    public MascotaEntidad() {}



    public int getIdMascota() { return idMascota; }
    public String getNombre() { return nombre; }
    public String getRaza() { return raza; }
    public String getEspecie() { return especie; }
    public String getEdad() { return edad; }
    public String getTamano() { return tamano; }
    public String getSexo() { return sexo; }
    public String getEstadoSalud() { return estadoSalud; }
    public String getUbicacion() { return ubicacion; }
    public String getEstado() { return estado; }
    public String getNombreRefugio() { return nombreRefugio; }
    public String getFotoPrincipalBase64() { return fotoPrincipalBase64; }
    public String getHistoria() { return historia; }
    public String getRequisitos() { return requisitosAdopcion; }



}