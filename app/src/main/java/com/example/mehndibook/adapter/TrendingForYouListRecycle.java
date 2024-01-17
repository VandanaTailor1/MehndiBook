package com.example.mehndibook.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mehndibook.R;
import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.util.RecycleItemClick;

import java.util.List;

public class TrendingForYouListRecycle extends RecyclerView.Adapter<TrendingForYouListRecycle.RecycleManagerSelect>{
    Context context;
    List<HomeModel.Data.TrendingDesign> trendingDesigns;
    RecycleItemClick recycleItemClick;
    String imgURL ="";

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public TrendingForYouListRecycle(Context context, List<HomeModel.Data.TrendingDesign> trendingDesigns, RecycleItemClick recycleItemClick) {
        this.context = context;
        this.trendingDesigns = trendingDesigns;
        this.recycleItemClick = recycleItemClick;
    }

    @NonNull
    @Override
    public RecycleManagerSelect onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_images_recycle,parent,false);
         RecycleManagerSelect recycleManagerSelect=new RecycleManagerSelect(v);
         return recycleManagerSelect;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleManagerSelect holder, int position) {
         holder.textView.setText(trendingDesigns.get(position).getTitle());
        Glide.with(context).load(imgURL+trendingDesigns.get(position).getFeaturePhoto())
                .placeholder(R.drawable.placeholder)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                       holder.progressBar.setVisibility(View.GONE);
                       return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            if(holder.getAdapterPosition()!=RecyclerView.NO_POSITION){
            recycleItemClick.onClick(holder.getAdapterPosition(),"trending_Recycle");
            }
        });

    }

    @Override
    public int getItemCount() {
        return trendingDesigns.size();
    }
    public class RecycleManagerSelect extends RecyclerView.ViewHolder {
        TextView textView;
        ProgressBar progressBar;
        ImageView imageView;
        public RecycleManagerSelect(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            textView=itemView.findViewById(R.id.text);
            progressBar=itemView.findViewById(R.id.progress);
        }

    }
}
