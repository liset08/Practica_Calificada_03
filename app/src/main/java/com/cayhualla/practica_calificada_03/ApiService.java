package com.cayhualla.practica_calificada_03;

import com.cayhualla.practica_calificada_03.models.Denuncias;
import com.cayhualla.practica_calificada_03.models.Usuarios;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**

/**
 * Created by Alumno on 15/05/2018.
 */

public interface ApiService {


        String API_BASE_URL = "https://productosapi-liset08.c9users.io";

        @GET("api/v1/denuncias")
        Call<List<Denuncias>> getDenuncias();

    @GET("api/v1/usuarios")
    Call<List<Usuarios>> getUsuarios();


    @FormUrlEncoded
    @POST("api/v1/usuarios")
    Call<ResponseMessage> createUsuarios(@Field("nombre") String nombre,
                                         @Field("apellido") String apellido,
                                         @Field("username") String username,
                                         @Field("password") String password,
                                         @Field("distrito") String distrito,
                                            @Field("tipo") int tipo );


    @FormUrlEncoded
    @POST("api/v1/denuncias")
    Call<ResponseMessage> createDenuncias(@Field("titulo") String titulo,
                                         @Field("descripcion") String descripcion,
                                         @Field("ubicacion") String ubicacion);

    @Multipart
    @POST("api/v1/denuncias ")
    Call<ResponseMessage> createDenunciaWhithImage(
            @Part("titulo") RequestBody nombre,
            @Part("descripcion") RequestBody apellido,
            @Part("ubicacion") RequestBody username,
            @Part MultipartBody.Part pfoto

            );

}


