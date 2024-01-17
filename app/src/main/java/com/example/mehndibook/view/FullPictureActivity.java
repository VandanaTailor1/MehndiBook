package com.example.mehndibook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mehndibook.R;
import com.example.mehndibook.databinding.ActivityFullPictureBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FullPictureActivity extends AppCompatActivity {
    ActivityFullPictureBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    String img=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullPictureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        backActivity();
        getImages();
        shareData();
        shareData();
        shareImg();
        statusBarColorChange();
   }

    public void getImages() {
        Bundle bundle = getIntent().getExtras();
        img = bundle.getString("key1");
        Glide.with(this).load(img).into(binding.img);
    }

    public void shareData() {
        Bundle bundle = getIntent().getExtras();
        img = bundle.getString("key1");
        sharedPreferences = getSharedPreferences("Image", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.contains(img)) {
            binding.heart.setImageResource(R.drawable.fillheart);
        } else {
            binding.heart.setImageResource(R.drawable.heart);
        }

        binding.heart.setOnClickListener(v -> {
            if (sharedPreferences.contains(img)) {
                editor.remove(img);
                editor.commit();
                binding.heart.setImageResource(R.drawable.heart);
            } else {
                editor.putString(img, img);
                editor.commit();
                binding.heart.setImageResource(R.drawable.fillheart);
            }
        });
    }
    public void downloadImage() {
        Bundle bundle = getIntent().getExtras();
        img = bundle.getString("key1");
        binding.download.setOnClickListener(v -> {
            String filename = "filename.jpg";
            File direct =
                    new File(Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                            .getAbsolutePath());
            if (!direct.exists()) {
                direct.mkdir();
            }
            DownloadManager dm = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(img);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(filename)
                    .setMimeType("image/jpg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,
                            File.separator + filename + File.separator);

            dm.enqueue(request);
        });
    }

    public void backActivity() {
        binding.arrow.setOnClickListener(v -> {
            finish();
        });
    }
    public void shareImg() {
        Bundle bundle = getIntent().getExtras();
        img = bundle.getString("key1");
        binding.share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
           Uri screenshotUri = Uri.parse(img);

                try {
                    InputStream stream = getContentResolver().openInputStream(screenshotUri);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                sharingIntent.setType("image/jpg");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, img);
                startActivity(Intent.createChooser(sharingIntent, "Share image using"));
            }
        });
    }
    public void statusBarColorChange() {
        if (Build.VERSION.SDK_INT >= 21) {
            WindowInsetsControllerCompat windowInsetsController =
                    WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
            windowInsetsController.setSystemBarsBehavior(
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            );
            getWindow().getDecorView().setOnApplyWindowInsetsListener((view, windowInsets) -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (windowInsets.isVisible(WindowInsetsCompat.Type.navigationBars())
                            || windowInsets.isVisible(WindowInsetsCompat.Type.statusBars())) {
                        binding.img.setOnClickListener(v -> {
                              windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
                            if(binding.relative.getVisibility()==View.VISIBLE) {
                                binding.relative.setVisibility(View.GONE);
                            }
                            else if(binding.relative.getVisibility()==View.GONE){
                                binding.relative.setVisibility(View.VISIBLE);
                            }
                        });
                    } else {
                        binding.img.setOnClickListener(v -> {
                             windowInsetsController.show(WindowInsetsCompat.Type.systemBars());
                            if(binding.relative.getVisibility()==View.VISIBLE) {
                                binding.relative.setVisibility(View.GONE);
                            }
                            else if(binding.relative.getVisibility()==View.GONE){
                                binding.relative.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }
                return view.onApplyWindowInsets(windowInsets);
            });
        }
    }
//    @Override
//    protected void onResume() {
//        checkLikeByposition();
//        super.onResume();
//    }
//
//    private void checkLikeByposition() {
//        if (sharedPreferences.contains(img)) {
//            binding.heart.setImageResource(R.drawable.fillheart);
//        } else {
//            binding.heart.setImageResource(R.drawable.heart);
//        }
//    }
}