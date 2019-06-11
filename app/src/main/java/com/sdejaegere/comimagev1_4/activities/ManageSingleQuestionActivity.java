package com.sdejaegere.comimagev1_4.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.sdejaegere.comimagev1_4.R;

public class ManageSingleQuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = ManageSingleQuestionActivity.this;
    private AppCompatButton appCompatButtonModifierModifyQuestion;
    private AppCompatButton appCompatButtonSupprimerModifyQuestion;
    private AppCompatButton appCompatButtonAnnulerModifyQuestion;
    private TextView laQuestion;

    private String idQuestionFromIntent;
    private String textQuestionFromIntent;
    private String idUserFromIntent;
    private String emailFromIntent;

    private int pictoUnFromIntent;
    private int pictoDeuxFromIntent;
    private int pictoTroisFromIntent;
    private int pictoQuatreFromIntent;
    private int pictoCinqFromIntent;

    private String nomPictoUnFromIntent;
    private String nomPictoDeuxFromIntent;
    private String nomPictoTroisFromIntent;
    private String nomPictoQuatreFromIntent;
    private String nomPictoCinqFromIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_manage_single_question);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();

    }

    private void initViews() {
        appCompatButtonModifierModifyQuestion = findViewById(R.id.appCompatButtonModifierModifyQuestion);
        appCompatButtonSupprimerModifyQuestion = findViewById(R.id.appCompatButtonSupprimerModifyQuestion);
        appCompatButtonAnnulerModifyQuestion = findViewById(R.id.appCompatButtonAnnulerModifyQuestion);
        laQuestion =  findViewById(R.id.textViewModifyQuestion);
    }

    private void initListeners() {
        appCompatButtonModifierModifyQuestion.setOnClickListener(this);
        appCompatButtonSupprimerModifyQuestion.setOnClickListener(this);
        appCompatButtonAnnulerModifyQuestion.setOnClickListener(this);

    }

    private void initObjects() {
        idQuestionFromIntent = getIntent().getStringExtra("IDQUESTION");
        textQuestionFromIntent = getIntent().getStringExtra("TXTQUESTION");
        idUserFromIntent = getIntent().getStringExtra("IDUSER");
        emailFromIntent = getIntent().getStringExtra("EMAIL");
        Bundle bundle = getIntent().getExtras();
        pictoUnFromIntent = bundle.getInt("CHOIXUN");
        pictoDeuxFromIntent = bundle.getInt("CHOIXDEUX");
        pictoTroisFromIntent = bundle.getInt("CHOIXTROIS");
        pictoQuatreFromIntent = bundle.getInt("CHOIXQUATRE");
        pictoCinqFromIntent = bundle.getInt("CHOIXCINQ");
        nomPictoUnFromIntent = getIntent().getStringExtra("NOMCHOIXUN");
        nomPictoDeuxFromIntent = getIntent().getStringExtra("NOMCHOIXDEUX");
        nomPictoTroisFromIntent = getIntent().getStringExtra("NOMCHOIXTROIS");
        nomPictoQuatreFromIntent = getIntent().getStringExtra("NOMCHOIXQUATRE");
        nomPictoCinqFromIntent = getIntent().getStringExtra("NOMCHOIXCINQ");
        laQuestion.setText(textQuestionFromIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonAnnulerModifyQuestion:
                onBackPressed();
                break;

            case R.id.appCompatButtonModifierModifyQuestion:
                Intent intentModifySingleQuestion = new Intent(getApplicationContext(), ModifiySingleQuestionActivity.class);
                intentModifySingleQuestion.putExtra("IDQUESTION", idQuestionFromIntent);
                intentModifySingleQuestion.putExtra("TXTQUESTION", textQuestionFromIntent);
                intentModifySingleQuestion.putExtra("IDUSER", idUserFromIntent);
                intentModifySingleQuestion.putExtra("CHOIXUN", pictoUnFromIntent);
                intentModifySingleQuestion.putExtra("CHOIXDEUX", pictoDeuxFromIntent);
                intentModifySingleQuestion.putExtra("CHOIXTROIS", pictoTroisFromIntent);
                intentModifySingleQuestion.putExtra("CHOIXQUATRE", pictoQuatreFromIntent);
                intentModifySingleQuestion.putExtra("CHOIXCINQ", pictoCinqFromIntent);
                intentModifySingleQuestion.putExtra("NOMCHOIXUN", nomPictoUnFromIntent);
                intentModifySingleQuestion.putExtra("NOMCHOIXDEUX", nomPictoDeuxFromIntent);
                intentModifySingleQuestion.putExtra("NOMCHOIXTROIS", nomPictoTroisFromIntent);
                intentModifySingleQuestion.putExtra("NOMCHOIXQUATRE", nomPictoQuatreFromIntent);
                intentModifySingleQuestion.putExtra("NOMCHOIXCINQ", nomPictoCinqFromIntent);
                startActivity(intentModifySingleQuestion);
                break;

            case R.id.appCompatButtonSupprimerModifyQuestion:
                Intent intentDeleteSingleQuestion = new Intent(getApplicationContext(), DeleteSingleQuestionActivity.class);
                intentDeleteSingleQuestion.putExtra("IDQUESTION", idQuestionFromIntent);
                intentDeleteSingleQuestion.putExtra("TXTQUESTION", textQuestionFromIntent);
                intentDeleteSingleQuestion.putExtra("IDUSER", idUserFromIntent);
                intentDeleteSingleQuestion.putExtra("EMAIL", emailFromIntent);
                startActivity(intentDeleteSingleQuestion);
                break;
        }
    }


}
