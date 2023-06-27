package com.example.clonebankingmobile;

import com.example.clonebankingmobile.api.AuthAPI;
import com.example.clonebankingmobile.api.UserAPI;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NetworkService {
    private static NetworkService networkService;
    private Retrofit retrofit;
    private static final String BASE_URL = "http://10.0.2.2:8080/";


    private NetworkService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

    public AuthAPI getAuthAPI(){
        return retrofit.create(AuthAPI.class);
    }
    public UserAPI getUserAPI(){
        return retrofit.create(UserAPI.class);
    }
}
