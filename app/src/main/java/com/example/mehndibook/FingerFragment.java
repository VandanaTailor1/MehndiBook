package com.example.mehndibook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mehndibook.adapter.FingerAdapter;
import com.example.mehndibook.databinding.FragmentFingerBinding;

public class FingerFragment extends Fragment {

    FragmentFingerBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentFingerBinding.inflate(inflater, container, false);
        View v=binding.getRoot();
        forYouFirstRecycle();
        return v;
    }
    public void forYouFirstRecycle(){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        binding.recycle.setLayoutManager(gridLayoutManager);

        FingerAdapter fingerFragment =new FingerAdapter(getContext());
        binding.recycle.setAdapter(fingerFragment);
    }
}