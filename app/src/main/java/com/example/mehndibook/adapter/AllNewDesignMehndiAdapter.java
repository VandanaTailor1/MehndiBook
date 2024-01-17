package com.example.mehndibook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mehndibook.R;
import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.util.RecycleItemClick;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AllNewDesignMehndiAdapter extends RecyclerView.Adapter<AllNewDesignMehndiAdapter.RecycleManagerSelect> {

    Context context;
    ArrayList<HomeModel.Data.NewDesign.Photos> newImg;
    String link;
    RecycleItemClick recycleItemClick;

    public AllNewDesignMehndiAdapter(Context context, ArrayList<HomeModel.Data.NewDesign.Photos> newImg, String link, RecycleItemClick recycleItemClick) {
        this.context = context;
        this.newImg = newImg;
        this.link = link;
        this.recycleItemClick = recycleItemClick;
    }

    @NonNull
    @Override
    public RecycleManagerSelect onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_mehndi_design_recycler,parent,false);
        RecycleManagerSelect recycleManagerSelect=new RecycleManagerSelect(v);
        return recycleManagerSelect;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleManagerSelect holder, int position) {
        Glide.with(context).load(link+newImg.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            if(holder.getAdapterPosition()!=RecyclerView.NO_POSITION){
                recycleItemClick.onClick(holder.getAdapterPosition(),"allNewImage");
            }
        });
    }

    @Override
    public int getItemCount() {
        return newImg.size();
    }

    public class RecycleManagerSelect extends RecyclerView.ViewHolder {
        ImageView imageView;
        public RecycleManagerSelect(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
        }
    }
 }
