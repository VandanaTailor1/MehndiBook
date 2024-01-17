package com.example.mehndibook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mehndibook.R;
import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.util.RecycleItemClick;
import com.example.mehndibook.view.ViewImagesActivity;

import java.util.ArrayList;
import java.util.List;

public class AllMehndipicRecycler extends RecyclerView.Adapter<AllMehndipicRecycler.RecycleManagerSelect> {

    Context context;
    ArrayList<HomeModel.Data.TrendingDesign.Photos> img;
    String link;
    RecycleItemClick recycleItemClick;

    public AllMehndipicRecycler(Context context, ArrayList<HomeModel.Data.TrendingDesign.Photos> img, String link, RecycleItemClick recycleItemClick) {
        this.context = context;
        this.img = img;
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
        Glide.with(context).load(link+img.get(position).getImage())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);
         holder.itemView.setOnClickListener(v -> {
             if(holder.getAdapterPosition()!=RecyclerView.NO_POSITION){
                 recycleItemClick.onClick(holder.getAdapterPosition(),"allMehndi_Recycle");
             }

         });
    }

    @Override
    public int getItemCount() {
        return img.size();
    }

    public class RecycleManagerSelect extends RecyclerView.ViewHolder {
        ImageView imageView;

        public RecycleManagerSelect(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
        }
    }
}
