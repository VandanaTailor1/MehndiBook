package com.example.mehndibook.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.repository.RepositoryClass;
import com.example.mehndibook.retrofit.ApiListner;
import com.example.mehndibook.util.CONSTANT;

public class ForYouViewModel extends ViewModel {
    RepositoryClass repositoryClass;
    MutableLiveData<HomeModel> homeModel;
    MutableLiveData<String> failuer=new MutableLiveData<>();

    public MutableLiveData<HomeModel> getHomeModel() {
        if (homeModel == null) {
            homeModel = new MutableLiveData<>();
        }
        return homeModel;
    }

    public MutableLiveData<String> getFailuer() {
        return failuer;
    }

    public void initForRepo() {
        repositoryClass = new RepositoryClass();
    }

    ApiListner apiListner = new ApiListner() {
        @Override
        public void onResponse(Object object, String picture) {
            if(picture.equalsIgnoreCase("picturegate")){
              homeModel.postValue((HomeModel) object);
            }
            else if(picture.equalsIgnoreCase(CONSTANT.trending)){
                homeModel.postValue((HomeModel) object);
            }
            else if(picture.equalsIgnoreCase(CONSTANT.newDesign)){
                homeModel.postValue((HomeModel) object);
            } else if (picture.equalsIgnoreCase(CONSTANT.designCollection)) {
                homeModel.postValue((HomeModel) object);
            }

        }
     @Override
        public void onFailure(String string) {
          failuer.postValue(string);
        }
    };

    public void getAPIsCall(){
         repositoryClass.getforYouImages(apiListner);
    }


}
