package com.example.mehndibook.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mehndibook.model.CategoriesModel;
import com.example.mehndibook.repository.CategoryRepoClass;
import com.example.mehndibook.retrofit.ApiListner;
import com.example.mehndibook.util.CONSTANT;

public class CategoryViewModel extends ViewModel{
    CategoryRepoClass categoryRepoClass;
    MutableLiveData<CategoriesModel> categoriesModelMutableLiveData ;
    public MutableLiveData<CategoriesModel> getCategoriesModelMutableLiveData() {
        if(categoriesModelMutableLiveData==null){
            categoriesModelMutableLiveData=new MutableLiveData<>();
        }
        return categoriesModelMutableLiveData;
    }
    MutableLiveData<String> onFailure =new MutableLiveData<>();
    public MutableLiveData<String> getOnFailure() {
        return onFailure;
    }
    public void intiRepo(){
        categoryRepoClass=new CategoryRepoClass();
    }
    ApiListner apiListner=new ApiListner() {
        @Override
        public void onResponse(Object object, String picture) {
            if(picture.equalsIgnoreCase(CONSTANT.CATEGORIES)){
                categoriesModelMutableLiveData.postValue((CategoriesModel) object);
            }
        }
        @Override
        public void onFailure(String string) {
        onFailure.postValue(string);
        }
    };
    public void getAPIS(int id){
        categoryRepoClass.getCategoriesImages(id,apiListner);
    }

}
