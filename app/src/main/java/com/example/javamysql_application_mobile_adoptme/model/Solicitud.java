package com.example.javamysql_application_mobile_adoptme.model;

public class Solicitud {

    private int idSolicitud;
    private int idUsuario;
    private int idMascota;
    private String fechaSolicitud;
    private String estadoSolicitud;
    private String comentarios;


    public Solicitud() {}


    public Solicitud(int idSolicitud, int idUsuario, int idMascota, String fechaSolicitud,
                     String estadoSolicitud, String comentarios) {
        this.idSolicitud = idSolicitud;
        this.idUsuario = idUsuario;
        this.idMascota = idMascota;
        this.fechaSolicitud = fechaSolicitud;
        this.estadoSolicitud = estadoSolicitud;
        this.comentarios = comentarios;
    }


    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
