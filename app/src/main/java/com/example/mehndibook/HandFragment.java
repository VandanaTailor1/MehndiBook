package com.example.mehndibook;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mehndibook.adapter.CategoriesAdapter;
import com.example.mehndibook.databinding.FragmentHandBinding;
import com.example.mehndibook.model.CategoriesModel;
import com.example.mehndibook.util.CONSTANT;
import com.example.mehndibook.util.RecycleItemClick;
import com.example.mehndibook.view.CategoriesActivity;
import com.example.mehndibook.view.ViewImagesActivity;
import com.example.mehndibook.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class HandFragment extends Fragment implements RecycleItemClick{
    FragmentHandBinding binding;
    CategoryViewModel categoryViewModel;
    List<CategoriesModel.Data.Design> categorieList=new ArrayList<>();
    String imgUrl;
    Bundle bundle=new Bundle();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding=FragmentHandBinding.inflate(inflater, container, false);
        View v=binding.getRoot();
        initCategoryModel();
        getAPI();
        attachObserver();
        return v;
    }
    @Override
    public void onClick(int position, String type) {
        if(type=="open_new") {
            Intent intent = new Intent(getContext(), ViewImagesActivity.class);
            startActivity(intent);
        }
    }
    public void initCategoryModel(){
        categoryViewModel=new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        categoryViewModel.intiRepo();
    }
    public void getAPI(){
        String id= bundle.getString(CONSTANT.ID,"0");
        if (id!="0")
        {
            categoryViewModel.getAPIS(Integer.parseInt(id));
        }
        else {
//            Toast.makeText(getContext(), "Error fetching category id", Toast.LENGTH_SHORT).show();
        }
    }
    public void attachObserver(){
        categoryViewModel.getCategoriesModelMutableLiveData().observe(getActivity(),categoriesModel -> {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
            binding.recycle.setLayoutManager(gridLayoutManager);
            categorieList=categoriesModel.getData().getDesigns();
            imgUrl=categoriesModel.getData().getImage_url();
            CategoriesAdapter handMehndiAdapter=new CategoriesAdapter(getContext(),categorieList,this::onClick);
            handMehndiAdapter.setImgURL(imgUrl);
            binding.recycle.setAdapter(handMehndiAdapter);
        });
    }
}