package com.example.javamysql_application_mobile_adoptme.model;

import java.util.List;

public class ChatResponse {

    private boolean success;
    private List<ChatMessage> data;
    private String mensaje;

    public boolean isSuccess() {
        return success;
    }

    public List<ChatMessage> getData() {
        return data;
    }

    public String getMensaje() {
        return mensaje;
    }
}
