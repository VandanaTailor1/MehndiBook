package com.example.mehndibook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mehndibook.R;
import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.util.RecycleItemClick;

import java.util.List;

public class ForYouListRecycler1 extends RecyclerView.Adapter<ForYouListRecycler1.RecycleManagerSelect> {
    Context context;
    List<HomeModel.Data.Categories> homedata;
    String imgUrl="";

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    RecycleItemClick recycleItemClick;

    public ForYouListRecycler1(Context context, List<HomeModel.Data.Categories> homedata, RecycleItemClick recycleItemClick) {
        this.context = context;
        this.homedata = homedata;
        this.recycleItemClick = recycleItemClick;
    }

    @NonNull
    @Override
    public RecycleManagerSelect onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.for_you_first_recycler,parent,false);
        RecycleManagerSelect recycleManagerSelect=new RecycleManagerSelect(v);
        return recycleManagerSelect;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleManagerSelect holder, int position) {
       holder.textView.setText(""+ homedata.get(position).getTitle());
        Glide.with(context).load(imgUrl+homedata.get(position).getBanner())
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);
       holder.imageView.setOnClickListener(v -> {
           if(holder.getAdapterPosition()!=RecyclerView.NO_POSITION){
               recycleItemClick.onClick(holder.getAdapterPosition(),"forYou_Recycle");
           }
       });
    }

    @Override
    public int getItemCount() {
        return homedata.size();
    }

    public class RecycleManagerSelect extends RecyclerView.ViewHolder {
       TextView textView;
       ImageView imageView;
        public RecycleManagerSelect(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
