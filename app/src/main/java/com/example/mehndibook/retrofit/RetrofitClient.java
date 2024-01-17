package com.example.mehndibook.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit;
    public static Retrofit getInstance(){

        return retrofit= new Retrofit.Builder()
                .baseUrl("https://mehndidesign.lekhajokha.org/index.php/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
