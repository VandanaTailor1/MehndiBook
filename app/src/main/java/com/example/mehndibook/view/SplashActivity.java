package com.example.mehndibook.view;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.mehndibook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity {
    String msg_token = "AAAAKuBzxys:APA91bHEu-negsTKUkFtn5MCRpCJAl8iKN93YhRAXG-fdPnd8eg80KFuqPwDQVsSLnxu0CKdASrsRAZwi63FKvgyPPos1wJaxSTtv15r7co7Y6ltrYqSmQxq6Cvq_oj10R1SiVFMAjfoAAAAKuBzxys:APA91bHEu-negsTKUkFtn5MCRpCJAl8iKN93YhRAXG-fdPnd8eg80KFuqPwDQVsSLnxu0CKdASrsRAZwi63FKvgyPPos1wJaxSTtv15r7co7Y6ltrYqSmQxq6Cvq_oj10R1SiVFMAjfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initilaization();
        getNotification();
        //  statusBarColorChange();
    }

    public void initilaization() {
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashActivity.this,
                        HomeActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }

    public void statusBarColorChange() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.TRANSPARENT);
            WindowCompat.setDecorFitsSystemWindows(window, false);
        }
    }

    public void getNotification() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if(!task.isSuccessful()){
                            Log.w("fgd", "Fetching FCM registration token failed", task.getException());
                               return;
                        }
                        String token=task.getResult();
                        Log.d("1232123212223344",token);
                    }
                });
    }

}
