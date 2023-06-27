package com.example.clonebankingmobile.api;

import com.example.clonebankingmobile.requests.ChangeEmailRequest;
import com.example.clonebankingmobile.responses.UserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPI {
    @GET("users/{id}")
    Call<UserResponse> getUserInfo(@Path("id") Long id, @Header("Authorization") String token);
    @POST("users/{id}/email")
    Call<ResponseBody> updateUserEmail(@Body ChangeEmailRequest request, @Path("id") Long id, @Header("Authorization") String token);
    @GET("users/{id}/image")
    Call<ResponseBody> getUserImage(@Path("id") Long id, @Header("Authorization") String token);
}
