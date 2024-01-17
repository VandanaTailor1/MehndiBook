package com.example.mehndibook.repository;

import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.retrofit.ApiListner;
import com.example.mehndibook.retrofit.InterfaceImages;
import com.example.mehndibook.retrofit.RetrofitClient;
import com.example.mehndibook.util.CONSTANT;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryClass {
  //  InterfaceDemo interfaceDemo= RetrofitClient.getInstance().create(InterfaceDemo.class);

    InterfaceImages interfaceImages= RetrofitClient.getInstance().create(InterfaceImages.class);
    public void getforYouImages(ApiListner apiListner){
         interfaceImages.getHomeImages()
                 .enqueue(new Callback<HomeModel>() {
                     @Override
                     public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                        if(response.isSuccessful()){
                      apiListner.onResponse(response.body(),"picturegate");
                        }else if (response.isSuccessful()){
                          apiListner.onResponse(response.body(), CONSTANT.trending);
                        }else if(response.isSuccessful()){
                            apiListner.onResponse(response.body(),CONSTANT.newDesign);
                        }else if(response.isSuccessful()){
                            apiListner.onResponse(response.body(),CONSTANT.designCollection);
                        }
                        else{
                            apiListner.onFailure(response.message());
                        }
                     }

                     @Override
                     public void onFailure(Call<HomeModel> call, Throwable t) {
                      apiListner.onFailure(t.getMessage());
                     }
                 });
    }
}
