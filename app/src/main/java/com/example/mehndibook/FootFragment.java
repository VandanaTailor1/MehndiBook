package com.example.mehndibook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mehndibook.adapter.FootAdapter;
import com.example.mehndibook.databinding.FragmentFootBinding;

public class FootFragment extends Fragment {
    FragmentFootBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding=FragmentFootBinding.inflate(inflater, container, false);
      View v=binding.getRoot();
      forYouFirstRecycle();
      return v;
    }
    public void forYouFirstRecycle(){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        binding.recycle.setLayoutManager(gridLayoutManager);

        FootAdapter footAdapter=new FootAdapter(getContext());
        binding.recycle.setAdapter(footAdapter);
    }
}