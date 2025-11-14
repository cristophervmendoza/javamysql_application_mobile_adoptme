package com.example.javamysql_application_mobile_adoptme.service;

import com.example.javamysql_application_mobile_adoptme.model.CatalogoRespuesta;
import com.example.javamysql_application_mobile_adoptme.model.BaseResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AdopcionApiService {


    @GET("api_mascotas.php")
    Call<CatalogoRespuesta> getCatalogoMascotas();

    @FormUrlEncoded
    @POST("toggle_favorito.php")
    Call<BaseResponse> toggleFavorite(@Field("id_usuario") int userId, @Field("id_mascota") int mascotaId);


    @GET("listar_favoritos.php")
    Call<CatalogoRespuesta> getFavoriteMascotas(@Query("id_usuario") int userId);



}