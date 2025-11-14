package com.example.javamysql_application_mobile_adoptme.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliente {

  
    private static final String BASE_URL = "https://adoptme-backendphp-emfwe5fbg5f8gpc6.chilecentral-01.azurewebsites.net/";

    private static Retrofit retrofit = null;

    public static Retrofit getCliente() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static AdopcionApiService getApiService() {
        return getCliente().create(AdopcionApiService.class);
    }
}