package com.sdejaegere.comimagev1_4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageView;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sdejaegere.comimagev1_4.R;

public class StartActivity extends AppCompatActivity {

    private static int SPLASH_TIME = 5000;
    AppCompatImageView ImageViewStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();

        //lien entre la variable ImageViewStart et son pendant visuel
        ImageViewStart = findViewById(R.id.ImageViewStart);

        //création d'une animation de type "fadein" qui durera 4 secondes (voir code dans dossier anim)
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadein);

        //on lance l'animation
        ImageViewStart.startAnimation(animation);

        //on lance le loginactivity une fois le temps correspondant à SPLASH_TIME passé (soit 5 secondes)
        //grâce à la méthode postDelayed de Handler()
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mySuperIntent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(mySuperIntent);

                finish();
            }
        }, SPLASH_TIME);
    }
}
