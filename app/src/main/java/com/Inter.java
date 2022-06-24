package com;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Inter {
    @FormUrlEncoded
@POST("Resigter.php")
    Call<Resigetrrespones>register(
            @Field("username")String username,
            @Field("email")String email,
            @Field("password")String password

);
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginRespones>login(
            @Field("email")String email,
            @Field("password")String password

    );

}
