package com.example.mehndibook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.mehndibook.databinding.ActivityFavouriteBinding;
import com.example.mehndibook.util.RecycleItemClick;

public class FavouriteActivity extends AppCompatActivity implements RecycleItemClick {
     ActivityFavouriteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        backData();
        addRecycle();
    }
    public void addRecycle(){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        binding.recycle.setLayoutManager(gridLayoutManager);

//        HandMehndiAdapter handMehndiAdapter=new HandMehndiAdapter(this,this::onClick);
//        binding.recycle.setAdapter(handMehndiAdapter);
    }

    public void backData(){
        binding.arrow.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void onClick(int position, String type) {
        if(type=="open_new"){
            Intent intent = new Intent(this, ViewImagesActivity.class);
            startActivity(intent);
        }
    }
}