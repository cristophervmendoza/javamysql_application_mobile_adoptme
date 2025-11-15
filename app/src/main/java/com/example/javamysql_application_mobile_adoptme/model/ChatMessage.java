package com.example.javamysql_application_mobile_adoptme.model;

public class ChatMessage {

    private int idMensaje;
    private int idEmisor;
    private int idReceptor;
    private String contenidoMensaje;
    private String fecha;

    public ChatMessage() {}

    public int getIdMensaje() { return idMensaje; }
    public void setIdMensaje(int idMensaje) { this.idMensaje = idMensaje; }

    public int getIdEmisor() { return idEmisor; }
    public void setIdEmisor(int idEmisor) { this.idEmisor = idEmisor; }

    public int getIdReceptor() { return idReceptor; }
    public void setIdReceptor(int idReceptor) { this.idReceptor = idReceptor; }

    public String getContenidoMensaje() { return contenidoMensaje; }
    public void setContenidoMensaje(String contenidoMensaje) { this.contenidoMensaje = contenidoMensaje; }


}
