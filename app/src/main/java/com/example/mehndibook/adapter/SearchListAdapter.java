package com.example.mehndibook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mehndibook.R;
import com.example.mehndibook.util.RecycleItemClick;

import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.RecycleManagerSelect> {
    Context context;
  List<String> text3;

 RecycleItemClick recycleItemClick;
    public SearchListAdapter(Context context,List<String> text3,  RecycleItemClick recycleItemClick) {
        this.context = context;
        this.text3 = text3;
        this.recycleItemClick=recycleItemClick;
    }

    @NonNull
    @Override
    public RecycleManagerSelect onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_list,parent,false);
       RecycleManagerSelect recycleManagerSelect=new RecycleManagerSelect(v);
        return recycleManagerSelect;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleManagerSelect holder, int position) {
         holder.textView.setText(text3.get(position));
    }

    @Override
    public int getItemCount() {
        return text3.size();
    }

    public class RecycleManagerSelect extends RecyclerView.ViewHolder {
        TextView textView;
        public RecycleManagerSelect(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
