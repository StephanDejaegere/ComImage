package com.sdejaegere.comimagev1_4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.WindowManager;

import com.sdejaegere.comimagev1_4.R;

public class CreditsActivity extends AppCompatActivity implements View.OnClickListener{

    private AppCompatButton appCompatButtonAnnulerCredits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_credits);
        getSupportActionBar().hide();
        initViews();
        initListeners();
    }

    private void initViews() {
        appCompatButtonAnnulerCredits = findViewById(R.id.appCompatButtonAnnulerCredits);
    }

    private void initListeners() {
        appCompatButtonAnnulerCredits.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonAnnulerCredits:
                onBackPressed();
                break;

        }
    }
}
