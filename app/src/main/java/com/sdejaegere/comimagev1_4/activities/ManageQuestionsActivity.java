package com.sdejaegere.comimagev1_4.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.adapters.QuestionsRecyclerAdapter;
import com.sdejaegere.comimagev1_4.model.Question;
import com.sdejaegere.comimagev1_4.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageQuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = ManageQuestionsActivity.this;


    private RecyclerView recyclerViewQuestions;
    private List<Question> listQuestions;
    private QuestionsRecyclerAdapter questionsRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    private AppCompatButton appCompatButtonAnnulerGererQuestion;

    private String idFromIntent;
    private String emailFromIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_manage_questions);
        getSupportActionBar().hide();
        initViews();
        initObjects();
        initListeners();
    }

    private void initViews() {
        appCompatButtonAnnulerGererQuestion = findViewById(R.id.appCompatButtonAnnulerGererQuestion);
        recyclerViewQuestions =  findViewById(R.id.recyclerViewQuestions);
    }

    private void initListeners() {
        appCompatButtonAnnulerGererQuestion.setOnClickListener(this);
        recyclerViewQuestions.setOnClickListener(this);
    }

    /**
     * Méthode pour initialiser les objets
     */
    private void initObjects() {
        listQuestions = new ArrayList<>();
        questionsRecyclerAdapter = new QuestionsRecyclerAdapter(this, listQuestions);

        idFromIntent = getIntent().getStringExtra("IDUSER");
        emailFromIntent = getIntent().getStringExtra("EMAIL");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewQuestions.setLayoutManager(mLayoutManager);
        recyclerViewQuestions.setItemAnimator(new DefaultItemAnimator());
        recyclerViewQuestions.setHasFixedSize(true);
        recyclerViewQuestions.setAdapter(questionsRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        getDataFromSQLite();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonAnnulerGererQuestion:
                //onBackPressed();
                Intent intentRetourMenuQuestion = new Intent(getApplicationContext(), MenuActivity.class);
                intentRetourMenuQuestion.putExtra("IDUSER", idFromIntent);
                intentRetourMenuQuestion.putExtra("EMAIL", emailFromIntent);
                startActivity(intentRetourMenuQuestion);

                break;

        }
    }

    /**
     * Méthode pour récupérer les données de la bdd
     */
    private void getDataFromSQLite() {

        // AsyncTask est utilisé de sorte que l'opération SQLite ne bloque pas le thread UI
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listQuestions.clear();
                listQuestions.addAll(databaseHelper.getSpecificUserQuestion(idFromIntent));

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                questionsRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


}
