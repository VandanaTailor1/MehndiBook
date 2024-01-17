package com.example.mehndibook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mehndibook.R;
import com.example.mehndibook.adapter.NotificationAdapter;
import com.example.mehndibook.adapter.PopularQandAAdapter;
import com.example.mehndibook.databinding.ActivityNotificationBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
  List<String> list =new ArrayList<>();
    ActivityNotificationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        callPopularQandAAdapter();
        backtoActivity();

    }
    public void callPopularQandAAdapter(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        binding.recycle.setLayoutManager(layoutManager);
        list.add("Trending");
        list.add("Comment");
        list.add("UPVOte");
        list.add("Trending");
        list.add("Trending");
        list.add("Trending");
        list.add("Trending");
        list.add("Trending");
        NotificationAdapter notificationAdapter=new NotificationAdapter(this,list);
        binding.recycle.setAdapter(notificationAdapter);
    }
    public void backtoActivity(){
        binding.arrow.setOnClickListener(v -> {
            finish();
        });
    }
    public void statusBarColorChange(){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.TRANSPARENT);
            WindowCompat.setDecorFitsSystemWindows(window, false);
        }
    }

}