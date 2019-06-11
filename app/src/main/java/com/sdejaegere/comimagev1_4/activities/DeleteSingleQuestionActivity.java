package com.sdejaegere.comimagev1_4.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.helpers.InputValidation;
import com.sdejaegere.comimagev1_4.model.Question;
import com.sdejaegere.comimagev1_4.sql.DatabaseHelper;

public class DeleteSingleQuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = DeleteSingleQuestionActivity.this;
    private AppCompatButton appCompatButtonSupprimerSingleQuestion;
    private AppCompatButton appCompatButtonAnnulerSupprimerSingleQuestion;
    private TextView textViewDeleteSingleQuestion;

    private String idQuestionFromIntent;
    private String textQuestionFromIntent;
    private String idUserFromIntent;
    private String emailFromIntent;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_delete_single_question);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        appCompatButtonSupprimerSingleQuestion = findViewById(R.id.appCompatButtonSupprimerSingleQuestion);
        appCompatButtonAnnulerSupprimerSingleQuestion = findViewById(R.id.appCompatButtonAnnulerSupprimerSingleQuestion);
        textViewDeleteSingleQuestion = findViewById(R.id.textViewDeleteSingleQuestion);

    }

    private void initListeners() {
        appCompatButtonSupprimerSingleQuestion.setOnClickListener(this);
        appCompatButtonAnnulerSupprimerSingleQuestion.setOnClickListener(this);
    }

    private void initObjects() {
        idQuestionFromIntent = getIntent().getStringExtra("IDQUESTION");
        textQuestionFromIntent = getIntent().getStringExtra("TXTQUESTION");
        idUserFromIntent = getIntent().getStringExtra("IDUSER");
        databaseHelper = new DatabaseHelper(activity);
        emailFromIntent = databaseHelper.getUserEmailFromId(idUserFromIntent);
        textViewDeleteSingleQuestion.setText(textQuestionFromIntent);

        inputValidation = new InputValidation(activity);
        question = new Question();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonAnnulerSupprimerSingleQuestion:
                Intent intentCancelDeleteSingleQuestion = new Intent(getApplicationContext(), ManageQuestionsActivity.class);
                intentCancelDeleteSingleQuestion.putExtra("IDQUESTION", idQuestionFromIntent);
                intentCancelDeleteSingleQuestion.putExtra("TXTQUESTION", textQuestionFromIntent);
                intentCancelDeleteSingleQuestion.putExtra("IDUSER", idUserFromIntent);
                intentCancelDeleteSingleQuestion.putExtra("EMAIL", emailFromIntent);
                startActivity(intentCancelDeleteSingleQuestion);
                break;

            case R.id.appCompatButtonSupprimerSingleQuestion:

                postDataToSQLite();
                Intent intentDeleteSingleQuestion = new Intent(getApplicationContext(), ManageQuestionsActivity.class);
                intentDeleteSingleQuestion.putExtra("IDQUESTION", idQuestionFromIntent);
                intentDeleteSingleQuestion.putExtra("TXTQUESTION", textQuestionFromIntent);
                intentDeleteSingleQuestion.putExtra("IDUSER", idUserFromIntent);
                intentDeleteSingleQuestion.putExtra("EMAIL", emailFromIntent);
                startActivity(intentDeleteSingleQuestion);
                break;
        }
    }

    private void postDataToSQLite(){

        question.setId(Integer.parseInt(idQuestionFromIntent));
        databaseHelper.deleteQuestion(question);

    }
}
