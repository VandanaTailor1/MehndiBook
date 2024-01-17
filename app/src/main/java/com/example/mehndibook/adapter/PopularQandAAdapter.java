package com.example.mehndibook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehndibook.R;

public class PopularQandAAdapter extends RecyclerView.Adapter<PopularQandAAdapter.RecycleManagerSelect> {
    Context context;

    public PopularQandAAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleManagerSelect onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popolar_question_answer,parent,false);
         RecycleManagerSelect recycleManagerSelect=new RecycleManagerSelect(v);
        return recycleManagerSelect;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleManagerSelect holder, int position) {
        holder.imageView.setImageDrawable(context.getDrawable(R.drawable.image6));
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class RecycleManagerSelect extends RecyclerView.ViewHolder {
        ImageView imageView;

        public RecycleManagerSelect(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
