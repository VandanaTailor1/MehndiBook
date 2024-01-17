package com.example.mehndibook.retrofit;

import com.example.mehndibook.model.CategoriesModel;
import com.example.mehndibook.model.HomeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterfaceImages {
    @GET("home")
    Call<HomeModel> getHomeImages();

    @GET("category-by-designs/{id}")
    Call<CategoriesModel> getCategories(
            @Path("id" ) int id
    );

}
