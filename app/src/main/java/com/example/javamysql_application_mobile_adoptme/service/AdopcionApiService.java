package com.example.javamysql_application_mobile_adoptme.service;

import com.example.javamysql_application_mobile_adoptme.model.CatalogoRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AdopcionApiService {


    @GET("api_mascotas.php")
    Call<CatalogoRespuesta> getCatalogoMascotas();
}