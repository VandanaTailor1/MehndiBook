package com.example.locationtrack;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

 public class ListDataAdapter extends FirebaseRecyclerAdapter<Model,ListDataAdapter.RecycleViewSelect> {
     Context context;
     ItemClickListner itemClickListner;
     public ListDataAdapter(@NonNull FirebaseRecyclerOptions<Model> options, Context context, ItemClickListner itemClickListner) {
         super(options);
         this.context = context;
         this.itemClickListner = itemClickListner;
     }

//     @Override
//     protected void onBindViewHolder(@NonNull ListDataAdapter.RecycleViewSelect holder, int position, @NonNull Model model) {
//         holder.getName.setText(model.getName());
//         holder.getContact.setText(model.getContact());
//       }
@Override
protected void onBindViewHolder(@NonNull RecycleViewSelect holder, int position, @NonNull Model model) {
         holder.getName.setText(model.getName());
         holder.getContact.setText(model.getContact());
         holder.itemView.setOnClickListener(v -> {
             itemClickListner.onItemClick(holder.getAdapterPosition(),"type");
         });
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


