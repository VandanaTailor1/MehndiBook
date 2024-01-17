package com.example.mehndibook.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mehndibook.adapter.CategoriesAdapter;
import com.example.mehndibook.databinding.ActivityHandDesignBinding;
import com.example.mehndibook.model.CategoriesModel;
import com.example.mehndibook.util.CONSTANT;
import com.example.mehndibook.util.RecycleItemClick;
import com.example.mehndibook.viewmodel.CategoryViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements RecycleItemClick {
ActivityHandDesignBinding binding;
CategoryViewModel categoryViewModel;
List<CategoriesModel.Data.Design> designList =new ArrayList<>();
    Bundle bundle=new Bundle();
String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityHandDesignBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
       bundle = getIntent().getExtras();
        binding.text.setText(bundle.getString(CONSTANT.NAME," "));
        backData();
        initCategoryModel();
        getAPI();
        attachObserver();
    }

    public void backData(){
        binding.arrow.setOnClickListener(v -> {
            finish();
        });
    }
    public void initCategoryModel(){
        categoryViewModel=new ViewModelProvider(CategoriesActivity.this).get(CategoryViewModel.class);
        categoryViewModel.intiRepo();
    }
    public void getAPI(){
       String id= bundle.getString(CONSTANT.ID,"0");
       if (id!="0")
       {
           categoryViewModel.getAPIS(Integer.parseInt(id));
       }
       else {
           Toast.makeText(this, "Error fetching category id", Toast.LENGTH_SHORT).show();
       }
    }
    public void attachObserver(){
        categoryViewModel.getCategoriesModelMutableLiveData().observe(this,categoriesModel -> {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
            binding.recycle.setLayoutManager(gridLayoutManager);
            designList=categoriesModel.getData().getDesigns();
            imgUrl=categoriesModel.getData().getImage_url();
            CategoriesAdapter handMehndiAdapter=new CategoriesAdapter(this,designList,this::onClick);
            handMehndiAdapter.setImgURL(imgUrl);
            binding.recycle.setAdapter(handMehndiAdapter);
        });
    }
    @Override
    public void onClick(int position, String type) {
        if(type=="open_new"){
            Intent intent = new Intent(this, ViewImagesActivity.class);
            intent.putExtra("photos",(Serializable) designList.get(position).getPhotos());
            intent.putExtra(CONSTANT.LINK,imgUrl);
            intent.putExtra(CONSTANT.TYPE, CONSTANT.CATEGORIESDATA);
             startActivity(intent);
        }
    }
}