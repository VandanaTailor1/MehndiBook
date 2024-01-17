package com.example.mehndibook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.WindowCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mehndibook.R;
import com.example.mehndibook.TabAdapterNew;
import com.example.mehndibook.TabLayOutAdapter;
import com.example.mehndibook.databinding.ActivityHomeBinding;
import com.example.mehndibook.model.CategoriesModel;
import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.viewmodel.ForYouViewModel;
import com.google.android.material.tabs.TabLayout;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.BuildConfig;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    ForYouViewModel forYouViewModel;
    HomeModel myhomeModel;
    TabLayOutAdapter tabLayOutAdapter;
    int pos = 0;
    List<CategoriesModel.Data.Design.Photos> categoriesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initForViewMode();
        tabLayoutOpen();
        attachObserver();
        ApiCall();
        openNavigationDrawer();
        notificationCall();
        searchBar();
        clickonNavigationviews();
    }

    public void initTabLayout() {
        binding.tablayout.addTab(binding.tablayout.newTab().setText("For you"));
        for (int i = 0; i < myhomeModel.getData().getCategories().size(); i++) {
            binding.tablayout.addTab(binding.tablayout.newTab().setText(myhomeModel.getData().getCategories().get(i).getTitle()));
        }
    tabLayOutAdapter=new TabLayOutAdapter(HomeActivity.this,getSupportFragmentManager(),binding.tablayout.getTabCount());
        binding.viewpager.setAdapter(tabLayOutAdapter);
        binding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void tabLayoutOpen() {
        binding.tablayout.setTabGravity(binding.tablayout.GRAVITY_START);
        binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tablayout));
        binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void ApiCall() {
        forYouViewModel.getAPIsCall();
    }

    public void initForViewMode() {
        forYouViewModel = new ViewModelProvider(this).get(ForYouViewModel.class);
        forYouViewModel.initForRepo();
    }

    public void attachObserver() {
        forYouViewModel.getHomeModel().observe(HomeActivity.this, homeModel -> {
            myhomeModel = homeModel;
            initTabLayout();
        });
        forYouViewModel.getFailuer().observe(HomeActivity.this, s -> {
            Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
        });
    }
    private void openNavigationDrawer() {
        binding.sidebar.setOnClickListener(v -> {
            binding.drawer.openDrawer(GravityCompat.START);
        });
    }
    private void clickonNavigationviews() {
        binding.nvg.bringToFront();
        View view1 = findViewById(R.id.homeLinear);
        view1.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            binding.drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);
        });
        View view2 = findViewById(R.id.favLinear);
        view2.setOnClickListener(v -> {
            Intent intent = new Intent(this, FavouriteActivity.class);
            binding.drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);
        });
        View view3 = findViewById(R.id.notificationLinear);
        view3.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            binding.drawer.closeDrawer(GravityCompat.START);
            startActivity(intent);
        });
        View view4 = findViewById(R.id.referLinear);
        view4.setOnClickListener(v -> {
            Drawable image = getDrawable(R.drawable.img_home);
            Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
            Intent sendIntent = new Intent();

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "title");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    values);
            OutputStream outstream;
            try {
                outstream = getContentResolver().openOutputStream(uri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
                outstream.close();
            } catch (Exception e) {
                System.err.println(e.toString());
            }

            sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=com.example.mehndibook");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Share Image"));
        });

    }
    private void notificationCall() {
        binding.notification.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        });

    }
    private void searchBar() {
        binding.linear.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });
    }
}
