package com.example.mehndibook.repository;

import android.util.Log;

import com.example.mehndibook.model.CategoriesModel;
import com.example.mehndibook.retrofit.ApiListner;
import com.example.mehndibook.retrofit.InterfaceImages;
import com.example.mehndibook.retrofit.RetrofitClient;
import com.example.mehndibook.util.CONSTANT;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepoClass {
    InterfaceImages interfaceImages= RetrofitClient.getInstance().create(InterfaceImages.class);

    public void getCategoriesImages(int id,ApiListner apiListner){
        interfaceImages.getCategories(id)
                .enqueue(new Callback<CategoriesModel>() {
                    @Override
                    public void onResponse(Call<CategoriesModel> call, Response<CategoriesModel> response) {
                        if(response.isSuccessful()){
                            apiListner.onResponse(response.body(), CONSTANT.CATEGORIES);
                        }else{
                            apiListner.onFailure(response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<CategoriesModel> call, Throwable t) {
                          apiListner.onFailure(t.getMessage());
                    }
                });
    }
}
