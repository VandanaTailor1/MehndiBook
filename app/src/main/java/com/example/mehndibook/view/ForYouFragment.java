package com.example.mehndibook.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mehndibook.adapter.ForYouListRecycler1;
import com.example.mehndibook.adapter.NewDesignListRecycle;
import com.example.mehndibook.adapter.TrendingForYouListRecycle;
import com.example.mehndibook.databinding.FragmentForYouBinding;
import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.util.CONSTANT;
import com.example.mehndibook.util.RecycleItemClick;
import com.example.mehndibook.viewmodel.ForYouViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ForYouFragment extends Fragment implements RecycleItemClick {
    FragmentForYouBinding binding;
    int pos=0;
    ForYouViewModel forYouViewModel;
    Context context;
    HomeModel homeModelData ;
   List<HomeModel.Data.Categories> categoriesList=new ArrayList<>();
   List<HomeModel.Data.TrendingDesign> trendingDesignArrayList=new ArrayList<HomeModel.Data.TrendingDesign>();
    List<HomeModel.Data.NewDesign> newDesignList=new ArrayList<>();
    String baseURL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
           binding= FragmentForYouBinding.inflate(inflater, container, false);
           View v=binding.getRoot();
          initForViewMode();
          context=v.getContext();
         attachCategoriesObserver();
         attachTrendingDesign();
        attachNewDesign();
//        ApiCall();
              return v;
    }
    private void ApiCall() {
        forYouViewModel.getAPIsCall();
    }

    public void initForViewMode(){
         forYouViewModel =new ViewModelProvider(getActivity()).get(ForYouViewModel.class);
        forYouViewModel.initForRepo();
    }
    private void attachCategoriesObserver() {
        forYouViewModel.getHomeModel().observe(getActivity(), homeModel -> {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
            binding.recycle1.setLayoutManager(gridLayoutManager);
            categoriesList = homeModel.getData().getCategories();
            ForYouListRecycler1 forYouListRecycler1 = new ForYouListRecycler1(getContext(), categoriesList, this::onClick);
            forYouListRecycler1.setImgUrl(homeModel.getData().getImageUrl());
            binding.recycle1.setAdapter(forYouListRecycler1);
        });
    }
        private void attachTrendingDesign() {

          forYouViewModel.getHomeModel().observe(getActivity(),homeModel -> {
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                binding.recycle2.setLayoutManager(linearLayoutManager);
                trendingDesignArrayList = homeModel.getData().getTrendingDesign();
                TrendingForYouListRecycle trendingForYouListRecycle=new TrendingForYouListRecycle(getContext(),trendingDesignArrayList,this::onClick);
                 baseURL=homeModel.getData().getImageUrl();
                 trendingForYouListRecycle.setImgURL(baseURL);
                binding.recycle2.setAdapter(trendingForYouListRecycle);
          });
        }

    private void attachNewDesign() {
        forYouViewModel.getHomeModel().observe(getActivity(),homeModel -> {
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
            binding.recycle3.setLayoutManager(linearLayoutManager);
            newDesignList=homeModel.getData().getNewDesign();
            NewDesignListRecycle newDesignListRecycle=new NewDesignListRecycle(getContext(),newDesignList,this::onClick);
            baseURL=homeModel.getData().getImageUrl();
            newDesignListRecycle.setImgURL(baseURL);
            binding.recycle3.setAdapter(newDesignListRecycle);
        });
    }

    @Override
    public void onClick(int position, String type) {
        if (type == "trending") {

        } else if (type == "trending_Recycle") {
           Intent intent = new Intent(getContext(), ViewImagesActivity.class);
           intent.putExtra("photos",(Serializable) trendingDesignArrayList.get(position).getPhotos());
           intent.putExtra(CONSTANT.LINK,baseURL);
           intent.putExtra(CONSTANT.TYPE, CONSTANT.TRENDING);
            context.startActivity(intent);
        } else if (type == "new_Recycle") {
            Intent intent = new Intent(getContext(), ViewImagesActivity.class);
          intent.putExtra("photos",(Serializable) newDesignList.get(position).getPhotos());
          intent.putExtra(CONSTANT.LINK,baseURL);
            intent.putExtra(CONSTANT.TYPE, CONSTANT.NEW);
            context.startActivity(intent);
        }
      else if (type == "forYou_Recycle") {
            Bundle bundle = new Bundle();
            bundle.putString(CONSTANT.NAME, String.valueOf(categoriesList.get(position).getTitle()));
            bundle.putString(CONSTANT.ID, categoriesList.get(position).getId());
            Intent intent = new Intent(getContext(), CategoriesActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    }