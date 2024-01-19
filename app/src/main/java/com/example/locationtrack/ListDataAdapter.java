package com.example.locationtrack;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

 public class ListDataAdapter extends FirebaseRecyclerAdapter<Model,ListDataAdapter.RecycleViewSelect> {

     public ListDataAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
         super(options);
     }

     @Override
     protected void onBindViewHolder(@NonNull ListDataAdapter.RecycleViewSelect holder, int position, @NonNull Model model) {
         holder.getName.setText(model.getName());
         holder.getContact.setText(model.getContact());
     }

     @NonNull
     @Override
     public ListDataAdapter.RecycleViewSelect onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_data,parent,false);
        RecycleViewSelect recycleManagerSelect=new RecycleViewSelect(v);
        return recycleManagerSelect;
     }

     public class RecycleViewSelect  extends RecyclerView.ViewHolder{
         TextView getName,getContact;
         public RecycleViewSelect(@NonNull View itemView) {
             super(itemView);
             getName=itemView.findViewById(R.id.getNameId);
             getContact=itemView.findViewById(R.id.getContactId);
         }
     }
 }


