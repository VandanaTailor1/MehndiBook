package com.example.mehndibook.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mehndibook.R;
import com.example.mehndibook.adapter.AllMehndipicRecycler;
import com.example.mehndibook.adapter.AllNewDesignMehndiAdapter;
import com.example.mehndibook.adapter.CategoriesListAdapter;
import com.example.mehndibook.databinding.ActivityViewImagesBinding;
import com.example.mehndibook.model.CategoriesModel;
import com.example.mehndibook.model.HomeModel;
import com.example.mehndibook.util.CONSTANT;
import com.example.mehndibook.util.RecycleItemClick;
import com.example.mehndibook.viewmodel.ForYouViewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewImagesActivity extends AppCompatActivity implements RecycleItemClick {
    int pos = 0;
    ActivityViewImagesBinding binding;
    String link;
    String data;
    ArrayList<HomeModel.Data.TrendingDesign.Photos> trendingDesignList = new ArrayList<>();
    ArrayList<HomeModel.Data.NewDesign.Photos> newDesignList = new ArrayList<>();
    List<CategoriesModel.Data.Design.Photos> categoryDesignList=new ArrayList<>();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String mytype;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewImagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bundle = getIntent().getExtras();
        mytype = bundle.getString(CONSTANT.TYPE);
        backtoActivity();
        allTrendingDesignRecycle();
       downloadImage();
        shareData();
        otherActivity();
       clickListenrs();
       shareImg();
      // shareImg();

    }

    private void clickListenrs() {
        downloadImage();
        binding.download.setOnClickListener(v -> {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(data));
            request.setTitle("Image Download");
            request.setDescription("Downloading image");
            request.setMimeType("image/jpeg");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg");
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            if (downloadManager != null) {
                downloadManager.enqueue(request);
            }
     });


    }

    public void allTrendingDesignRecycle() {
        if (Objects.equals(mytype, CONSTANT.TRENDING)) {
            Intent intent = getIntent();
            trendingDesignList = (ArrayList<HomeModel.Data.TrendingDesign.Photos>) intent.getSerializableExtra("photos");
            link = intent.getStringExtra(CONSTANT.LINK);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            binding.recycle.setLayoutManager(layoutManager);
            AllMehndipicRecycler allMehndipicRecycler = new AllMehndipicRecycler(this, trendingDesignList, link, this::onClick);
            binding.recycle.setAdapter(allMehndipicRecycler);
        } else if (Objects.equals(mytype, CONSTANT.NEW)) {
            Intent intent = getIntent();
            newDesignList = (ArrayList<HomeModel.Data.NewDesign.Photos>) intent.getSerializableExtra("photos");
             link = intent.getStringExtra(CONSTANT.LINK);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            binding.recycle.setLayoutManager(layoutManager);
            AllNewDesignMehndiAdapter allNewDesignMehndiAdapter = new AllNewDesignMehndiAdapter(this, newDesignList, link, this::onClick);
            binding.recycle.setAdapter(allNewDesignMehndiAdapter);
        }else if(Objects.equals(mytype,CONSTANT.CATEGORIESDATA)){
            Intent intent=getIntent();
            categoryDesignList= (List<CategoriesModel.Data.Design.Photos>) intent.getSerializableExtra("photos");
            link=intent.getStringExtra(CONSTANT.LINK);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
            binding.recycle.setLayoutManager(layoutManager);
            CategoriesListAdapter categoriesListAdapter=new CategoriesListAdapter(this,link,categoryDesignList,this::onClick);
            binding.recycle.setAdapter(categoriesListAdapter);
        }
    }
    public void downloadImage() {
        if (Objects.equals(mytype, CONSTANT.TRENDING)){
            data= trendingDesignList.get(pos).getImage();
                }
        else if (Objects.equals(mytype, CONSTANT.NEW)){
            data=newDesignList.get(pos).getImage();
              }
        else if (Objects.equals(mytype,CONSTANT.CATEGORIESDATA)) {
            data=categoryDesignList.get(pos).getImage();
        }
    }

    public void backtoActivity() {
        binding.arrow.setOnClickListener(v -> {
            finish();
        });
    }

    public void otherActivity() {
        binding.img.setOnClickListener(v -> {

            Intent intent = new Intent(this, FullPictureActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("key1", link + data);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
  public void shareData() {
      sharedPreferences = getSharedPreferences("Image", MODE_PRIVATE);
      editor = sharedPreferences.edit();
      if (sharedPreferences.contains(data)) {
          binding.heart.setImageResource(R.drawable.fillheart);
      } else {
          binding.heart.setImageResource(R.drawable.heart);
      }

      binding.heart.setOnClickListener(v -> {
          if (sharedPreferences.contains(data)) {
              editor.remove(data);
              editor.commit();
              binding.heart.setImageResource(R.drawable.heart);
          } else {
              editor.putString(data, data);
              editor.commit();
              binding.heart.setImageResource(R.drawable.fillheart);
          }
      });
  }


    @Override
    public void onClick(int position, String type) {
        pos = position;
        if (type == "allMehndi_Recycle") {
            checkLikeByposition(pos);
            Glide.with(ViewImagesActivity.this).load(link + trendingDesignList.get(position).getImage()).into(binding.img);
        } else if (type == "allNewImage") {
            checkLikeByposition(pos);
            Glide.with(ViewImagesActivity.this).load(link + newDesignList.get(position).getImage()).into(binding.img);
        }else if(type=="allCategoryImage"){
            checkLikeByposition(pos);
            Glide.with(ViewImagesActivity.this).load(link + categoryDesignList.get(position).getImage()).into(binding.img);
        }
        downloadImage();
    }
    public void shareImg() {
        binding.share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                Uri screenshotUri = Uri.parse(data);
                try {
                    InputStream stream = getContentResolver().openInputStream(screenshotUri);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                sharingIntent.setType("image/jpg");
                sharingIntent.putExtra(Intent.EXTRA_STREAM, data);
                startActivity(Intent.createChooser(sharingIntent, "Share image using"));
            }
        });
    }

    @Override
    protected void onResume() {
        checkLikeByposition(pos);
            Glide.with(ViewImagesActivity.this).load(link+ data).into(binding.img);
           super.onResume();
    }

    private void checkLikeByposition(int pos) {
            if (sharedPreferences.contains(data)) {
                binding.heart.setImageResource(R.drawable.fillheart);
            } else {
                binding.heart.setImageResource(R.drawable.heart);
            }
        }
}