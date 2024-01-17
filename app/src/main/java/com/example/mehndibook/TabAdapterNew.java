package com.example.mehndibook;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.view.ForYouFragment;

import java.util.ArrayList;
import java.util.List;

public class TabAdapterNew extends FragmentPagerAdapter {

    List<HomeModel.Data.Categories> categories = new ArrayList<>();

    public TabAdapterNew(
            @NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new ForYouFragment();
        else if (position == 1)
            fragment = new HandFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Algorithm";
        else if (position == 1)
            title = "Courses";
        else if (position == 2)
            title = "Login";
        return title;
    }

    public void setCatePhotosList(List<HomeModel.Data.Categories> categories) {
        this.categories=categories;
    }
}
