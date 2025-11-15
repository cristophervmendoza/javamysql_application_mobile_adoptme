package com.example.javamysql_application_mobile_adoptme.service;

import com.example.javamysql_application_mobile_adoptme.model.BaseResponse;
import com.example.javamysql_application_mobile_adoptme.model.ChatResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MensajesApiService {


    @FormUrlEncoded
    @POST("enviar_mensaje.php")
    Call<BaseResponse> enviarMensaje(
            @Field("emisor") int emisor,
            @Field("receptor") int receptor,
            @Field("mensaje") String mensaje
    );

    @GET("obtener_chat.php")
    Call<ChatResponse> obtenerChat(
            @Query("user") int user
    );


}
