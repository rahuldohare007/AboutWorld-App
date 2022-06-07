package com.example.aboutworld;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
        private static int SPLASH_SCREEN=3000;

        Animation topAnim, bottomAnim;
        ImageView img;
        TextView txtNameApp, txtSubNameApp, txtCopyright;

        @RequiresApi (api= Build.VERSION_CODES.P)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
            setContentView(R.layout.activity_main);

            topAnim= AnimationUtils.loadAnimation(context:this, R.anim.top_animation);
            bottomAnim= AnimationUtils.loadAnimation(context:this, R.anim.bottom_animation);

            img=findViewById(R.id.img_logo);
            txtNameApp=findViewById(R.id.tv_nameApp);
            txtSubNameApp=findViewById(R.id.tv_sub_nameApp);
            txtCopyright=findViewById(R.id.tv_sub_nameApp2);

            img.setAnimation(topAnim);
            txtNameApp.setAnimation(bottomAnim);
            txtSubNameApp.setAnimation(bottomAnim);
            txtCopyright.setAnimation(bottomAnim);

            new Handler().postDelayed((Runnable) () -> {
                Intent intent=new Intent( packageContext: MainActivity.this, LoginPage.class);
                startActivity(intent);
                finish();
            }, SPLASH_SCREEN);
        }
}