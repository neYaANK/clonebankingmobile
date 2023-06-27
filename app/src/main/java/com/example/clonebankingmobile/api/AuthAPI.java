package com.example.clonebankingmobile.api;

import com.example.clonebankingmobile.requests.CodeRequest;
import com.example.clonebankingmobile.requests.LoginRequest;
import com.example.clonebankingmobile.responses.JwtResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthAPI {
    @POST("auth/signin")
    Call<JwtResponse> loginUser(@Body LoginRequest request);

    @POST("auth/verify")
    Call<JwtResponse> verifyUser(@Body CodeRequest request, @Header("Authorization") String token);

}
