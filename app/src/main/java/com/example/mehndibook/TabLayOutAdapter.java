package com.example.mehndibook;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mehndibook.model.CategoriesModel;
import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.util.CONSTANT;
import com.example.mehndibook.view.ForYouFragment;
import com.example.mehndibook.view.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class TabLayOutAdapter extends FragmentStatePagerAdapter {
    int num=5;

    public TabLayOutAdapter(HomeActivity homeActivity, @NonNull FragmentManager fm, int tabCount) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ForYouFragment();
            default:
                return new FingerFragment();
        }
//        if (position == 0) {
//            return new ForYouFragment();
//        } else if (position==1){
//            return new HandFragment();}
//        else {
//            return null;
//
//            bundle.putString(CONSTANT.ID, catePhotosList.get(position - 1).getId());
//            HandFragment fragment = new HandFragment();
//            Log.e("uyfnvtvyutvniyt",""+catePhotosList.size());
//
//            fragment.setArguments(bundle);
//            return fragment;

    }
    @Override
    public int getCount() {
        return num;
    }
}
