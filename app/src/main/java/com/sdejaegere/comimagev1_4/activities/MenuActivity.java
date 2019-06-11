package com.sdejaegere.comimagev1_4.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.sql.DatabaseHelper;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = MenuActivity.this;

    private NestedScrollView nestedScrollView;

    private AppCompatButton appCompatButtonCreerQuestion;
    private AppCompatButton appCompatButtonManageQuestions;
    private AppCompatButton appCompatButtonManageProfil;

    private AppCompatTextView textViewLinkCredits;

    private DatabaseHelper databaseHelper;
    private String emailFromIntent;
    private String idFromIntent;
    private String nameFromIntent;

    AppCompatImageView logoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();

    }

    private void initViews() {

        nestedScrollView = findViewById(R.id.nestedScrollView);

        appCompatButtonCreerQuestion = findViewById(R.id.appCompatButtonCreerQuestion);
        appCompatButtonManageQuestions = findViewById(R.id.appCompatButtonGererQuestion);
        appCompatButtonManageProfil = findViewById(R.id.appCompatButtonGererProfil);
        textViewLinkCredits = findViewById(R.id.appCompatTextViewCredits);
        logoMenu = findViewById(R.id.logoMenu);

    }

    private void initListeners() {
        appCompatButtonCreerQuestion.setOnClickListener(this);
        appCompatButtonManageQuestions.setOnClickListener(this);
        appCompatButtonManageProfil.setOnClickListener(this);
        textViewLinkCredits.setOnClickListener(this);
        logoMenu.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        emailFromIntent = getIntent().getStringExtra("EMAIL");
        idFromIntent = databaseHelper.getUserId(emailFromIntent);
        nameFromIntent = databaseHelper.getUserName(emailFromIntent);

    }

    /**
     * MenuActivity se compose de quatre choix possible:
     * _ Créer une question
     * _ Gérer ses questions
     * _ Gérer son profil
     * _ Voir les crédits
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonCreerQuestion:
                Intent intentCreateQuestion = new Intent(getApplicationContext(), CreateQuestionActivity.class);
                intentCreateQuestion.putExtra("IDUSER", idFromIntent);
                intentCreateQuestion.putExtra("EMAIL", emailFromIntent);
                startActivity(intentCreateQuestion);
                break;

            case R.id.appCompatButtonGererQuestion:
                Intent intentManageQuestions = new Intent(getApplicationContext(), ManageQuestionsActivity.class);
                intentManageQuestions.putExtra("IDUSER", idFromIntent);
                intentManageQuestions.putExtra("EMAIL", emailFromIntent);
                startActivity(intentManageQuestions);
                break;

            case R.id.appCompatButtonGererProfil:
                Intent intentManageProfil = new Intent(getApplicationContext(), ManageProfilActivity.class);
                intentManageProfil.putExtra("IDUSER", idFromIntent);
                intentManageProfil.putExtra("EMAIL", emailFromIntent);
                intentManageProfil.putExtra("NAME", nameFromIntent);
                startActivity(intentManageProfil);
                break;

            case R.id.appCompatTextViewCredits:
                // Aller à CreditsActivity
                Intent intentCredits = new Intent(getApplicationContext(), CreditsActivity.class);
                intentCredits.putExtra("IDUSER", idFromIntent);
                intentCredits.putExtra("EMAIL", emailFromIntent);
                startActivity(intentCredits);
                break;

            case R.id.logoMenu:
                // Animation inutile et donc indispensable :)
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
                logoMenu.startAnimation(animation);


        }
    }
}
