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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.helpers.InputValidation;
import com.sdejaegere.comimagev1_4.model.Question;
import com.sdejaegere.comimagev1_4.sql.DatabaseHelper;

public class ModifiySingleQuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = ModifiySingleQuestionActivity.this;

    private AppCompatButton appCompatButtonModifySingleSaveQuestion;
    private AppCompatButton appCompatButtonAnnulerModifySingleQuestion;

    private TextInputLayout textInputLayoutModifySingleQuestion;
    private TextInputEditText textInputEditModifySingleQuestion;

    private ImageButton imageButtonChoixPictoUnModify;
    private ImageButton imageButtonChoixPictoDeuxModify;
    private ImageButton imageButtonChoixPictoTroisModify;
    private ImageButton imageButtonChoixPictoQuatreModify;
    private ImageButton imageButtonChoixPictoCinqModify;

    private TextView textViewNomPictoUnModify;
    private TextView textViewNomPictoDeuxModify;
    private TextView textViewNomPictoTroisModify;
    private TextView textViewNomPictoQuatreModify;
    private TextView textViewNomPictoCinqModify;

    private String idQuestionFromIntent;
    private String textQuestionFromIntent;
    private String idUserFromIntent;
    private String emailFromIntent;

    private int choixUn;
    private int choixDeux;
    private int choixTrois;
    private int choixQuatre;
    private int choixCinq;

    private String choixPictoUnFromIntent;
    private String choixPictoDeuxFromIntent;
    private String choixPictoTroisFromIntent;
    private String choixPictoQuatreFromIntent;
    private String choixPictoCinqFromIntent;
    private String nomPictoIntent;

    private int choix;
    private int bouton;

    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_modifiy_single_question);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();

        initViews();
        initListeners();
        initObjects();

        if (getIntent().hasExtra("CHOIX")) {
             choix = bundle.getInt("CHOIX");
             bouton = bundle.getInt("BOUTON");

            choixUn = bundle.getInt("CHOIXUN");
            choixDeux = bundle.getInt("CHOIXDEUX");
            choixTrois = bundle.getInt("CHOIXTROIS");
            choixQuatre = bundle.getInt("CHOIXQUATRE");
            choixCinq = bundle.getInt("CHOIXCINQ");
            choixPictoUnFromIntent = bundle.getString("NOMCHOIXUN");
            choixPictoDeuxFromIntent = bundle.getString("NOMCHOIXDEUX");
            choixPictoTroisFromIntent = bundle.getString("NOMCHOIXTROIS");
            choixPictoQuatreFromIntent = bundle.getString("NOMCHOIXQUATRE");
            choixPictoCinqFromIntent = bundle.getString("NOMCHOIXCINQ");
            textQuestionFromIntent = bundle.getString("QUESTION");
            //  Toast.makeText(CreateQuestionActivity.this, "t", Toast.LENGTH_SHORT).show();
            nomPictoIntent = bundle.getString("NOMPICTO");

            if (bouton == 1) {
                choixUn = choix;
                choixPictoUnFromIntent = nomPictoIntent;
            }
            if (bouton == 2) {
                choixDeux = choix;
                choixPictoDeuxFromIntent = nomPictoIntent;
            }
            if (bouton == 3) {
                choixTrois = choix;
                choixPictoTroisFromIntent = nomPictoIntent;
            }
            if (bouton == 4) {
                choixQuatre = choix;
                choixPictoQuatreFromIntent = nomPictoIntent;
            }
            if (bouton == 5) {
                choixCinq = choix;
                choixPictoCinqFromIntent = nomPictoIntent;
            }

            imageButtonChoixPictoUnModify.setImageResource(choixUn);
            imageButtonChoixPictoDeuxModify.setImageResource(choixDeux);
            imageButtonChoixPictoTroisModify.setImageResource(choixTrois);
            imageButtonChoixPictoQuatreModify.setImageResource(choixQuatre);
            imageButtonChoixPictoCinqModify.setImageResource(choixCinq);

            textViewNomPictoUnModify.setText(choixPictoUnFromIntent);
            textViewNomPictoDeuxModify.setText(choixPictoDeuxFromIntent);
            textViewNomPictoTroisModify.setText(choixPictoTroisFromIntent);
            textViewNomPictoQuatreModify.setText(choixPictoQuatreFromIntent);
            textViewNomPictoCinqModify.setText(choixPictoCinqFromIntent);

            textInputEditModifySingleQuestion.setText(textQuestionFromIntent);

        }

    }

    private void initViews() {
        appCompatButtonModifySingleSaveQuestion = findViewById(R.id.appCompatButtonModifySingleSaveQuestion);
        appCompatButtonAnnulerModifySingleQuestion = findViewById(R.id.appCompatButtonAnnulerModifySingleQuestion);

        textInputLayoutModifySingleQuestion = findViewById(R.id.textInputLayoutModifySingleQuestion);
        textInputEditModifySingleQuestion = findViewById(R.id.textInputEditModifySingleQuestion);

        imageButtonChoixPictoUnModify = findViewById(R.id.imageButtonChoixPictoUnModify);
        imageButtonChoixPictoDeuxModify = findViewById(R.id.imageButtonChoixPictoDeuxModify);
        imageButtonChoixPictoTroisModify = findViewById(R.id.imageButtonChoixPictoTroisModify);
        imageButtonChoixPictoQuatreModify = findViewById(R.id.imageButtonChoixPictoQuatreModify);
        imageButtonChoixPictoCinqModify = findViewById(R.id.imageButtonChoixPictoCinqModify);

        textViewNomPictoUnModify = findViewById(R.id.textViewNomPictoUnModify);
        textViewNomPictoDeuxModify = findViewById(R.id.textViewNomPictoDeuxModify);
        textViewNomPictoTroisModify = findViewById(R.id.textViewNomPictoTroisModify);
        textViewNomPictoQuatreModify = findViewById(R.id.textViewNomPictoQuatreModify);
        textViewNomPictoCinqModify = findViewById(R.id.textViewNomPictoCinqModify);

    }

    private void initListeners() {
        appCompatButtonModifySingleSaveQuestion.setOnClickListener(this);
        appCompatButtonAnnulerModifySingleQuestion.setOnClickListener(this);
        imageButtonChoixPictoUnModify.setOnClickListener(this);
        imageButtonChoixPictoDeuxModify.setOnClickListener(this);
        imageButtonChoixPictoTroisModify.setOnClickListener(this);
        imageButtonChoixPictoQuatreModify.setOnClickListener(this);
        imageButtonChoixPictoCinqModify.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        idQuestionFromIntent = getIntent().getStringExtra("IDQUESTION");
        textQuestionFromIntent = getIntent().getStringExtra("TXTQUESTION");
        idUserFromIntent = getIntent().getStringExtra("IDUSER");
        Bundle bundle = getIntent().getExtras();

        choixUn = bundle.getInt("CHOIXUN");
        choixDeux = bundle.getInt("CHOIXDEUX");
        choixTrois = bundle.getInt("CHOIXTROIS");
        choixQuatre = bundle.getInt("CHOIXQUATRE");
        choixCinq = bundle.getInt("CHOIXCINQ");

        choixPictoUnFromIntent = getIntent().getStringExtra("NOMCHOIXUN");
        choixPictoDeuxFromIntent = getIntent().getStringExtra("NOMCHOIXDEUX");
        choixPictoTroisFromIntent = getIntent().getStringExtra("NOMCHOIXTROIS");
        choixPictoQuatreFromIntent = getIntent().getStringExtra("NOMCHOIXQUATRE");
        choixPictoCinqFromIntent = getIntent().getStringExtra("NOMCHOIXCINQ");

        Log.d("*****CHOIXUN", choixUn+"");

        if(choixUn != 0){
            imageButtonChoixPictoUnModify.setImageResource(choixUn);
            textViewNomPictoUnModify.setText(choixPictoUnFromIntent);

        }

        if(choixDeux != 0){
            imageButtonChoixPictoDeuxModify.setImageResource(choixDeux);
            textViewNomPictoDeuxModify.setText(choixPictoDeuxFromIntent);
        }

        if(choixTrois != 0){
            imageButtonChoixPictoTroisModify.setImageResource(choixTrois);
            textViewNomPictoTroisModify.setText(choixPictoTroisFromIntent);
        }

        if(choixQuatre != 0){
            imageButtonChoixPictoQuatreModify.setImageResource(choixQuatre);
            textViewNomPictoQuatreModify.setText(choixPictoQuatreFromIntent);
        }

        if(choixCinq != 0){
            imageButtonChoixPictoCinqModify.setImageResource(choixCinq);
            textViewNomPictoCinqModify.setText(choixPictoCinqFromIntent);
        }

        emailFromIntent = databaseHelper.getUserEmailFromId(idUserFromIntent);
        textInputEditModifySingleQuestion.setText(textQuestionFromIntent);

        inputValidation = new InputValidation(activity);
        question = new Question();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonAnnulerModifySingleQuestion:
                Intent intentCancelModifySingleQuestion = new Intent(getApplicationContext(), ManageQuestionsActivity.class);
                intentCancelModifySingleQuestion.putExtra("IDQUESTION", idQuestionFromIntent);
                intentCancelModifySingleQuestion.putExtra("TXTQUESTION", textQuestionFromIntent);
                intentCancelModifySingleQuestion.putExtra("IDUSER", idUserFromIntent);
                intentCancelModifySingleQuestion.putExtra("EMAIL", emailFromIntent);
                startActivity(intentCancelModifySingleQuestion);
                break;

            case R.id.appCompatButtonModifySingleSaveQuestion:

                postDataToSQLite();
                Intent intentModifySingleQuestion = new Intent(getApplicationContext(), ManageQuestionsActivity.class);
                intentModifySingleQuestion.putExtra("IDQUESTION", idQuestionFromIntent);
                intentModifySingleQuestion.putExtra("TXTQUESTION", textQuestionFromIntent);
                intentModifySingleQuestion.putExtra("IDUSER", idUserFromIntent);
                intentModifySingleQuestion.putExtra("EMAIL", emailFromIntent);
                startActivity(intentModifySingleQuestion);
                break;

            case R.id.imageButtonChoixPictoUnModify:
                Intent accountsIntentPictoUn = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoUn.putExtra("IDUSER", idUserFromIntent);
                accountsIntentPictoUn.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoUn.putExtra("BOUTON", 1);
                accountsIntentPictoUn.putExtra("IDQUESTION", idQuestionFromIntent);
                accountsIntentPictoUn.putExtra("CHOIXUN", choixUn);
                accountsIntentPictoUn.putExtra("CHOIXDEUX", choixDeux);
                accountsIntentPictoUn.putExtra("CHOIXTROIS", choixTrois);
                accountsIntentPictoUn.putExtra("CHOIXQUATRE", choixQuatre);
                accountsIntentPictoUn.putExtra("CHOIXCINQ", choixCinq);
                accountsIntentPictoUn.putExtra("NOMCHOIXUN", choixPictoUnFromIntent);
                accountsIntentPictoUn.putExtra("NOMCHOIXDEUX", choixPictoDeuxFromIntent);
                accountsIntentPictoUn.putExtra("NOMCHOIXTROIS", choixPictoTroisFromIntent);
                accountsIntentPictoUn.putExtra("NOMCHOIXQUATRE", choixPictoQuatreFromIntent);
                accountsIntentPictoUn.putExtra("NOMCHOIXCINQ", choixPictoCinqFromIntent);
                accountsIntentPictoUn.putExtra("QUESTION", textQuestionFromIntent);
                startActivity(accountsIntentPictoUn);
                break;

            case R.id.imageButtonChoixPictoDeuxModify:
                Intent accountsIntentPictoDeux = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoDeux.putExtra("IDUSER", idUserFromIntent);
                accountsIntentPictoDeux.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoDeux.putExtra("BOUTON", 2);
                accountsIntentPictoDeux.putExtra("IDQUESTION", idQuestionFromIntent);
                accountsIntentPictoDeux.putExtra("CHOIXUN", choixUn);
                accountsIntentPictoDeux.putExtra("CHOIXDEUX", choixDeux);
                accountsIntentPictoDeux.putExtra("CHOIXTROIS", choixTrois);
                accountsIntentPictoDeux.putExtra("CHOIXQUATRE", choixQuatre);
                accountsIntentPictoDeux.putExtra("CHOIXCINQ", choixCinq);
                accountsIntentPictoDeux.putExtra("NOMCHOIXUN", choixPictoUnFromIntent);
                accountsIntentPictoDeux.putExtra("NOMCHOIXDEUX", choixPictoDeuxFromIntent);
                accountsIntentPictoDeux.putExtra("NOMCHOIXTROIS", choixPictoTroisFromIntent);
                accountsIntentPictoDeux.putExtra("NOMCHOIXQUATRE", choixPictoQuatreFromIntent);
                accountsIntentPictoDeux.putExtra("NOMCHOIXCINQ", choixPictoCinqFromIntent);
                accountsIntentPictoDeux.putExtra("QUESTION", textQuestionFromIntent);
                startActivity(accountsIntentPictoDeux);
                break;

            case R.id.imageButtonChoixPictoTroisModify:
                Intent accountsIntentPictoTrois = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoTrois.putExtra("IDUSER", idUserFromIntent);
                accountsIntentPictoTrois.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoTrois.putExtra("BOUTON", 3);
                accountsIntentPictoTrois.putExtra("IDQUESTION", idQuestionFromIntent);
                accountsIntentPictoTrois.putExtra("CHOIXUN", choixUn);
                accountsIntentPictoTrois.putExtra("CHOIXDEUX", choixDeux);
                accountsIntentPictoTrois.putExtra("CHOIXTROIS", choixTrois);
                accountsIntentPictoTrois.putExtra("CHOIXQUATRE", choixQuatre);
                accountsIntentPictoTrois.putExtra("CHOIXCINQ", choixCinq);
                accountsIntentPictoTrois.putExtra("NOMCHOIXUN", choixPictoUnFromIntent);
                accountsIntentPictoTrois.putExtra("NOMCHOIXDEUX", choixPictoDeuxFromIntent);
                accountsIntentPictoTrois.putExtra("NOMCHOIXTROIS", choixPictoTroisFromIntent);
                accountsIntentPictoTrois.putExtra("NOMCHOIXQUATRE", choixPictoQuatreFromIntent);
                accountsIntentPictoTrois.putExtra("NOMCHOIXCINQ", choixPictoCinqFromIntent);
                accountsIntentPictoTrois.putExtra("QUESTION", textQuestionFromIntent);
                startActivity(accountsIntentPictoTrois);
                break;

            case R.id.imageButtonChoixPictoQuatreModify:
                Intent accountsIntentPictoQuatre = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoQuatre.putExtra("IDUSER", idUserFromIntent);
                accountsIntentPictoQuatre.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoQuatre.putExtra("BOUTON", 4);
                accountsIntentPictoQuatre.putExtra("IDQUESTION", idQuestionFromIntent);
                accountsIntentPictoQuatre.putExtra("CHOIXUN", choixUn);
                accountsIntentPictoQuatre.putExtra("CHOIXDEUX", choixDeux);
                accountsIntentPictoQuatre.putExtra("CHOIXTROIS", choixTrois);
                accountsIntentPictoQuatre.putExtra("CHOIXQUATRE", choixQuatre);
                accountsIntentPictoQuatre.putExtra("CHOIXCINQ", choixCinq);
                accountsIntentPictoQuatre.putExtra("NOMCHOIXUN", choixPictoUnFromIntent);
                accountsIntentPictoQuatre.putExtra("NOMCHOIXDEUX", choixPictoDeuxFromIntent);
                accountsIntentPictoQuatre.putExtra("NOMCHOIXTROIS", choixPictoTroisFromIntent);
                accountsIntentPictoQuatre.putExtra("NOMCHOIXQUATRE", choixPictoQuatreFromIntent);
                accountsIntentPictoQuatre.putExtra("NOMCHOIXCINQ", choixPictoCinqFromIntent);
                accountsIntentPictoQuatre.putExtra("QUESTION", textQuestionFromIntent);
                startActivity(accountsIntentPictoQuatre);
                break;

            case R.id.imageButtonChoixPictoCinqModify:
                Intent accountsIntentPictoCinq = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoCinq.putExtra("IDUSER", idUserFromIntent);
                accountsIntentPictoCinq.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoCinq.putExtra("BOUTON", 5);
                accountsIntentPictoCinq.putExtra("IDQUESTION", idQuestionFromIntent);
                accountsIntentPictoCinq.putExtra("CHOIXUN", choixUn);
                accountsIntentPictoCinq.putExtra("CHOIXDEUX", choixDeux);
                accountsIntentPictoCinq.putExtra("CHOIXTROIS", choixTrois);
                accountsIntentPictoCinq.putExtra("CHOIXQUATRE", choixQuatre);
                accountsIntentPictoCinq.putExtra("CHOIXCINQ", choixCinq);
                accountsIntentPictoCinq.putExtra("NOMCHOIXUN", choixPictoUnFromIntent);
                accountsIntentPictoCinq.putExtra("NOMCHOIXDEUX", choixPictoDeuxFromIntent);
                accountsIntentPictoCinq.putExtra("NOMCHOIXTROIS", choixPictoTroisFromIntent);
                accountsIntentPictoCinq.putExtra("NOMCHOIXQUATRE", choixPictoQuatreFromIntent);
                accountsIntentPictoCinq.putExtra("NOMCHOIXCINQ", choixPictoCinqFromIntent);
                accountsIntentPictoCinq.putExtra("QUESTION", textQuestionFromIntent);
                startActivity(accountsIntentPictoCinq);
                break;

        }
    }

    private void postDataToSQLite(){

        if (!inputValidation.isInputEditTextFilled(textInputEditModifySingleQuestion, textInputLayoutModifySingleQuestion, getString(R.string.error_no_question))) {
            return;
        }

        question.setQuestion(textInputEditModifySingleQuestion.getText().toString().trim());
        question.setId(Integer.parseInt(idQuestionFromIntent));
        question.setUserId(idUserFromIntent);

       if(choixUn != 0){
           question.setPictoUn(choixUn);
           question.setNomPictoUn(choixPictoUnFromIntent);
       }

       if(choixDeux != 0){
           question.setPictoDeux(choixDeux);
           question.setNomPictoDeux(choixPictoDeuxFromIntent);
       }

       if(choixTrois != 0){
           question.setPictoTrois(choixTrois);
           question.setNomPictoTrois(choixPictoTroisFromIntent);
       }

       if(choixQuatre != 0){
           question.setPictoQuatre(choixQuatre);
           question.setNomPictoQuatre(choixPictoQuatreFromIntent);
       }

       if(choixCinq != 0){
           question.setPictoCinq(choixCinq);
           question.setNomPictoCinq(choixPictoCinqFromIntent);
       }

        databaseHelper.updateQuestion(question);


    }
}
