package com.example.mehndibook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mehndibook.R;
import com.example.mehndibook.adapter.HotCircleAdapter;
import com.example.mehndibook.adapter.NewDesignListRecycle;
import com.example.mehndibook.adapter.PopularQandAAdapter;
import com.example.mehndibook.adapter.SearchListAdapter;
import com.example.mehndibook.databinding.ActivitySearchBinding;
import com.example.mehndibook.util.RecycleItemClick;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements RecycleItemClick {

    ActivitySearchBinding binding;
    List<String> text3=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        callHotCircleAdapter();
        callPopularQandAAdapter();
        backActivity();
      searchrecycleCall();
      generalText();
    }

    public void generalText(){
        binding.edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                     if(s.toString().length()==0){
                         binding.recyclelist.setVisibility(View.GONE);
                         binding.recycleData.setVisibility(View.VISIBLE);
                     }else{
                         binding.recyclelist.setVisibility(View.VISIBLE);
                         binding.recycleData.setVisibility(View.GONE);
                     }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void searchrecycleCall() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
      binding.recyclelist.setLayoutManager(layoutManager);
        text3.add("this");
        text3.add("this");
        text3.add("this");
        text3.add("this");
        text3.add("this");
        text3.add("this");
        text3.add("this");
        text3.add("this");
        text3.add("this");
        SearchListAdapter searchListAdapter=new SearchListAdapter(this,text3,this::onClick);
        binding.recyclelist.setAdapter(searchListAdapter);
    }

    public void callHotCircleAdapter(){
       LinearLayoutManager layoutManager=new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
       binding.recycle.setLayoutManager(layoutManager);
       HotCircleAdapter hotCircleAdapter=new HotCircleAdapter(this);
       binding.recycle.setAdapter(hotCircleAdapter);
    }

    public void callPopularQandAAdapter(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        binding.recycle1.setLayoutManager(layoutManager);
        PopularQandAAdapter popularQandAAdapter=new PopularQandAAdapter(this);
        binding.recycle1.setAdapter(popularQandAAdapter);
    }
    public void backActivity(){
        binding.arrow.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void onClick(int position, String type) {
             if(type=="search_list"){

             }
    }
}