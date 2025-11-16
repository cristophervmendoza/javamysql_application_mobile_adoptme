package com.example.javamysql_application_mobile_adoptme.service;

import com.example.javamysql_application_mobile_adoptme.model.BaseResponse;
import com.example.javamysql_application_mobile_adoptme.model.CatalogoRespuesta;
import com.example.javamysql_application_mobile_adoptme.model.ChatResponse;
import com.example.javamysql_application_mobile_adoptme.model.FavoriteResponse;
import com.example.javamysql_application_mobile_adoptme.model.DetalleSolicitud;
import com.example.javamysql_application_mobile_adoptme.model.Solicitud;

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
    Call<BaseResponse> toggleFavorite(
            @Field("id_usuario") int userId,
            @Field("id_mascota") int mascotaId
    );

    @FormUrlEncoded
    @POST("listar_favoritos.php")
    Call<FavoriteResponse> listarFavoritos(
            @Field("id_usuario") int userId
    );

    @FormUrlEncoded
    @POST("enviar_mensaje.php")
    Call<BaseResponse> enviarMensaje(
            @Field("emisor") int emisor,
            @Field("receptor") int receptor,
            @Field("mensaje") String mensaje
    );

    @GET("obtener_chat.php")
    Call<ChatResponse> obtenerChat(@Query("user") int user);



    @FormUrlEncoded
    @POST("crear_solicitud.php")
    Call<BaseResponse> enviarSolicitud(
            @Field("id_usuario") int idUsuario,
            @Field("id_mascota") int idMascota,

            @Field("nombre_completo") String nombreCompleto,
            @Field("correo_electronico") String correo,
            @Field("telefono") String telefono,
            @Field("direccion") String direccion,

            @Field("tipo_vivienda") String tipoVivienda,
            @Field("composicion_familiar") String composicionFamiliar,
            @Field("ninos_en_hogar") String ninosHogar,
            @Field("alergias_animales") String alergias,

            @Field("otras_mascotas") String otrasMascotas,
            @Field("experiencia_prev") String experienciaPrev,
            @Field("tiempo_cuidado") String tiempoCuidado,

            @Field("motivo_adopcion") String motivo,
            @Field("plan_cuidado") String planCuidado,

            @Field("acepta_terminos") int aceptaTerminos,
            @Field("confirma_veracidad") int confirmaVeracidad
    );


    @GET("listar_solicitudes_usuario.php")
    Call<Solicitud[]> listarSolicitudes(@Query("id_usuario") int usuario);

    @GET("obtener_detalle_solicitud.php")
    Call<DetalleSolicitud> obtenerDetalle(@Query("id_solicitud") int solicitud);

}
