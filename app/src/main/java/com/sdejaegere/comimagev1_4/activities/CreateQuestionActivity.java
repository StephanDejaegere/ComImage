package com.sdejaegere.comimagev1_4.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.helpers.InputValidation;
import com.sdejaegere.comimagev1_4.model.Question;
import com.sdejaegere.comimagev1_4.sql.DatabaseHelper;

public class CreateQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    //définition d'une varaible Activité
    private final AppCompatActivity activity = CreateQuestionActivity.this;

    //définition des variables qui seront liées à leurs pendants visuels
    private TextInputLayout textInputLayoutQuestion;
    private TextInputEditText textInputEditTextQuestion;

    private AppCompatButton appCompatButtonSaveQuestion;
    private AppCompatButton appCompatButtonAnnulerCreerQuestion;

    private ImageButton imageButtonChoixPictoUn;
    private ImageButton imageButtonChoixPictoDeux;
    private ImageButton imageButtonChoixPictoTrois;
    private ImageButton imageButtonChoixPictoQuatre;
    private ImageButton imageButtonChoixPictoCinq;

    private TextView textViewNomPictoUn;
    private TextView textViewNomPictoDeux;
    private TextView textViewNomPictoTrois;
    private TextView textViewNomPictoQuatre;
    private TextView textViewNomPictoCinq;


    //définition des objets qui seront utilisés
    private Question question;
    private DatabaseHelper databaseHelper;
    private InputValidation inputValidation;

    //définition des nombreuses variables d'intent
    private String idFromIntent;
    private String emailFromIntent;
    private String choixPictoUnFromIntent;
    private String choixPictoDeuxFromIntent;
    private String choixPictoTroisFromIntent;
    private String choixPictoQuatreFromIntent;
    private String choixPictoCinqFromIntent;
    private int choixUn;
    private int choixDeux;
    private int choixTrois;
    private int choixQuatre;
    private int choixCinq;
    private String textQuestionFromIntent;
    private String nomPictoIntent;
    private int choix;
    private int bouton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_create_question);
        getSupportActionBar().hide();

        Bundle bundle = getIntent().getExtras();

        initViews();
        initListeners();
        initObjects();

        //ce if permet de garder en mémoire les choix faits entre cette activité et ChoiXPictoActivity
        //Si il n'y a pas d'intent "CHOIX", c'est la première fois que l'on arrive sur cette activité, on initialise les valeurs
        //par défaut, si l'intent "CHOIX" est présent, on récupère tous les intents nécessaires pour repeupler l'activité
        if(getIntent().hasExtra("CHOIX")){

            //choix est la variable contenant quel nouveau picto a été choisi
            //bouton est la variable permettant de savoir à quel bouton il faut lier choix
            //nomPictoIntent est la variable contenant le nom du nouveau picto choisi
            choix = bundle.getInt("CHOIX");
            bouton = bundle.getInt("BOUTON");
            nomPictoIntent = bundle.getString("NOMPICTO");

            //les cinq choixXXX gardent en mémoire les précédents choix pour chaque bouton
            choixUn = bundle.getInt("CHOIXUN");
            choixDeux = bundle.getInt("CHOIXDEUX");
            choixTrois = bundle.getInt("CHOIXTROIS");
            choixQuatre = bundle.getInt("CHOIXQUATRE");
            choixCinq = bundle.getInt("CHOIXCINQ");

            //les cinq ChoixPictoXXXFromIntent gardent en mémoire le nom des pictos précédemment choisis
            choixPictoUnFromIntent = bundle.getString("NOMCHOIXUN");
            choixPictoDeuxFromIntent = bundle.getString("NOMCHOIXDEUX");
            choixPictoTroisFromIntent = bundle.getString("NOMCHOIXTROIS");
            choixPictoQuatreFromIntent = bundle.getString("NOMCHOIXQUATRE");
            choixPictoCinqFromIntent = bundle.getString("NOMCHOIXCINQ");

            //on garde aussi en mémoire le texte de la question
            textQuestionFromIntent = bundle.getString("QUESTION");

                //on va modifier le variables choixXXX et choixPictoXXXFromIntent en fonction du bouton choisi
                if(bouton == 1){
                    choixUn = choix;
                    choixPictoUnFromIntent = nomPictoIntent;
                }
                if(bouton == 2){
                    choixDeux = choix;
                    choixPictoDeuxFromIntent = nomPictoIntent;
                }
                if(bouton == 3){
                    choixTrois = choix;
                    choixPictoTroisFromIntent = nomPictoIntent;
                }
                if(bouton == 4){
                    choixQuatre = choix;
                    choixPictoQuatreFromIntent = nomPictoIntent;
                }
                if(bouton == 5){
                    choixCinq = choix;
                    choixPictoCinqFromIntent = nomPictoIntent;
                }

            //enfin, on repeuple les données sur l'activité
            imageButtonChoixPictoUn.setImageResource(choixUn);
            imageButtonChoixPictoDeux.setImageResource(choixDeux);
            imageButtonChoixPictoTrois.setImageResource(choixTrois);
            imageButtonChoixPictoQuatre.setImageResource(choixQuatre);
            imageButtonChoixPictoCinq.setImageResource(choixCinq);
            textViewNomPictoUn.setText(choixPictoUnFromIntent);
            textViewNomPictoDeux.setText(choixPictoDeuxFromIntent);
            textViewNomPictoTrois.setText(choixPictoTroisFromIntent);
            textViewNomPictoQuatre.setText(choixPictoQuatreFromIntent);
            textViewNomPictoCinq.setText(choixPictoCinqFromIntent);
            textInputEditTextQuestion.setText(textQuestionFromIntent);


        }else{

            //ici, pas d'intent "CHOIX", on va donc initialiser les variables par défaut
            choixUn = R.drawable.add;
            choixDeux = R.drawable.add;
            choixTrois = R.drawable.add;
            choixQuatre = R.drawable.add;
            choixCinq = R.drawable.add;
            choixPictoUnFromIntent = "Pictogramme 1";
            choixPictoDeuxFromIntent = "Pictogramme 2";
            choixPictoTroisFromIntent = "Pictogramme 3";
            choixPictoQuatreFromIntent = "Pictogramme 4";
            choixPictoCinqFromIntent = "Pictogramme 5";
            textViewNomPictoUn.setText(choixPictoUnFromIntent);
            textViewNomPictoDeux.setText(choixPictoDeuxFromIntent);
            textViewNomPictoTrois.setText(choixPictoTroisFromIntent);
            textViewNomPictoQuatre.setText(choixPictoQuatreFromIntent);
            textViewNomPictoCinq.setText(choixPictoCinqFromIntent);
        }


    }

    private void initViews() {
        appCompatButtonSaveQuestion =  findViewById(R.id.appCompatButtonSaveQuestion);
        appCompatButtonAnnulerCreerQuestion =  findViewById(R.id.appCompatButtonAnnulerCreerQuestion);

        imageButtonChoixPictoUn = findViewById(R.id.imageButtonChoixPictoUn);
        imageButtonChoixPictoDeux = findViewById(R.id.imageButtonChoixPictoDeux);
        imageButtonChoixPictoTrois = findViewById(R.id.imageButtonChoixPictoTrois);
        imageButtonChoixPictoQuatre = findViewById(R.id.imageButtonChoixPictoQuatre);
        imageButtonChoixPictoCinq = findViewById(R.id.imageButtonChoixPictoCinq);

        textViewNomPictoUn = findViewById(R.id.textViewNomPictoUn);
        textViewNomPictoDeux = findViewById(R.id.textViewNomPictoDeux);
        textViewNomPictoTrois = findViewById(R.id.textViewNomPictoTrois);
        textViewNomPictoQuatre = findViewById(R.id.textViewNomPictoQuatre);
        textViewNomPictoCinq = findViewById(R.id.textViewNomPictoCinq);

        textInputEditTextQuestion =  findViewById(R.id.textInputEditTextQuestion);
        textInputLayoutQuestion =  findViewById(R.id.textInputLayoutQuestion);
    }

    private void initListeners() {
        appCompatButtonAnnulerCreerQuestion.setOnClickListener(this);
        appCompatButtonSaveQuestion.setOnClickListener(this);
        imageButtonChoixPictoUn.setOnClickListener(this);
        imageButtonChoixPictoDeux.setOnClickListener(this);
        imageButtonChoixPictoTrois.setOnClickListener(this);
        imageButtonChoixPictoQuatre.setOnClickListener(this);
        imageButtonChoixPictoCinq.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        question = new Question();
        idFromIntent = getIntent().getStringExtra("IDUSER");
        emailFromIntent = getIntent().getStringExtra("EMAIL");

        imageButtonChoixPictoUn.setImageResource(R.drawable.add);
        imageButtonChoixPictoDeux.setImageResource(R.drawable.add);
        imageButtonChoixPictoTrois.setImageResource(R.drawable.add);
        imageButtonChoixPictoQuatre.setImageResource(R.drawable.add);
        imageButtonChoixPictoCinq.setImageResource(R.drawable.add);


    }

    /**
     * De nombreux choix ici
     * deux boutons principaux, l'un pour sauvegarder (appellant PostDataToSQLite()), l'autre pour annuler (retournant à MEnuActivity)
     * Cinq ImageButton, pour ajouter jusqu'à cinq Pictos, allant à ChoixPictoActivity
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonSaveQuestion:
                postDataToSQLite();
                break;

            case R.id.appCompatButtonAnnulerCreerQuestion:
                Intent backToMenu = new Intent(activity, MenuActivity.class);
                backToMenu.putExtra("IDUSER", idFromIntent);
                backToMenu.putExtra("EMAIL", emailFromIntent);
                startActivity(backToMenu);
                break;

            case R.id.imageButtonChoixPictoUn:

                Intent accountsIntentPictoUn = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoUn.putExtra("IDUSER", idFromIntent);
                accountsIntentPictoUn.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoUn.putExtra("BOUTON", 1);
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
                accountsIntentPictoUn.putExtra("QUESTION", textInputEditTextQuestion.getText().toString().trim());
                startActivity(accountsIntentPictoUn);
                break;

            case R.id.imageButtonChoixPictoDeux:
                Intent accountsIntentPictoDeux = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoDeux.putExtra("IDUSER", idFromIntent);
                accountsIntentPictoDeux.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoDeux.putExtra("BOUTON", 2);
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
                accountsIntentPictoDeux.putExtra("QUESTION", textInputEditTextQuestion.getText().toString().trim());
                startActivity(accountsIntentPictoDeux);
                break;

            case R.id.imageButtonChoixPictoTrois:
                Intent accountsIntentPictoTrois = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoTrois.putExtra("IDUSER", idFromIntent);
                accountsIntentPictoTrois.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoTrois.putExtra("BOUTON", 3);
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
                accountsIntentPictoTrois.putExtra("QUESTION", textInputEditTextQuestion.getText().toString().trim());
                startActivity(accountsIntentPictoTrois);
                break;

            case R.id.imageButtonChoixPictoQuatre:
                Intent accountsIntentPictoQuatre = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoQuatre.putExtra("IDUSER", idFromIntent);
                accountsIntentPictoQuatre.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoQuatre.putExtra("BOUTON", 4);
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
                accountsIntentPictoQuatre.putExtra("QUESTION", textInputEditTextQuestion.getText().toString().trim());
                startActivity(accountsIntentPictoQuatre);
                break;

            case R.id.imageButtonChoixPictoCinq:
                Intent accountsIntentPictoCinq = new Intent(activity, ChoixPictoActivity.class);
                accountsIntentPictoCinq.putExtra("IDUSER", idFromIntent);
                accountsIntentPictoCinq.putExtra("EMAIL", emailFromIntent);
                accountsIntentPictoCinq.putExtra("BOUTON", 5);
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
                accountsIntentPictoCinq.putExtra("QUESTION", textInputEditTextQuestion.getText().toString().trim());
                startActivity(accountsIntentPictoCinq);
                break;
        }
    }

    /**
     * la méthode pour enregistrer la question créée.
     * On vérifie chaque ImageBouton pour voir si un choix a été fait, et dans ce cas on enregistre les choix effectués (picto et nom du picto)
     */
    private void postDataToSQLite(){

        //il faudra impérativement une question, on vérifie donc si elle a été saisie
        if (!inputValidation.isInputEditTextFilled(textInputEditTextQuestion, textInputLayoutQuestion, getString(R.string.error_no_question))) {
            return;
        }

        //on vérifie si la question n'existe pas déjà
        if (!databaseHelper.checkQuestion(textInputEditTextQuestion.getText().toString().trim(), Integer.parseInt(idFromIntent))){
            question.setQuestion(textInputEditTextQuestion.getText().toString().trim());
            question.setUserId(idFromIntent);
            if(choixUn != R.drawable.add){

                question.setPictoUn(choixUn);
                question.setNomPictoUn(choixPictoUnFromIntent);
            }else{
                question.setPictoUn(0);
                question.setNomPictoUn("");
            }

            if(choixDeux != R.drawable.add){

                question.setPictoDeux(choixDeux);
                question.setNomPictoDeux(choixPictoDeuxFromIntent);
            }else{
                question.setPictoDeux(0);
                question.setNomPictoDeux("");
            }

            if(choixTrois != R.drawable.add){

                question.setPictoTrois(choixTrois);
                question.setNomPictoTrois(choixPictoTroisFromIntent);
            }
            else{
                question.setPictoTrois(0);
                question.setNomPictoTrois("");
            }

            if(choixQuatre != R.drawable.add){

                question.setPictoQuatre(choixQuatre);
                question.setNomPictoQuatre(choixPictoQuatreFromIntent);
            }else{
                question.setPictoQuatre(0);
                question.setNomPictoQuatre("");
            }

            if(choixCinq != R.drawable.add){

                question.setPictoCinq(choixCinq);
                question.setNomPictoCinq(choixPictoCinqFromIntent);
            }else{
                question.setPictoCinq(0);
                question.setNomPictoCinq("");
            }

            databaseHelper.addQuestion(question);
            emptyInputEditText();
            hideKeyboard(activity);

            //une fois la question enregistrée, on retourne au Menu,e n gardant bien en mémoire qui utilise l'application, grâce aux intents
            Intent accountsIntent = new Intent(activity, MenuActivity.class);
            accountsIntent.putExtra("IDUSER", idFromIntent);
            accountsIntent.putExtra("EMAIL", emailFromIntent);
            startActivity(accountsIntent);
        }
        else
        {
            //si la question existe déjà
            hideKeyboard(activity);
            Toast.makeText(activity,getString(R.string.error_question_exist),
                    Toast.LENGTH_LONG).show();
            }


    }

    /**
     * méthode pour cacher le clavier virtuel
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Méthode pour "nettoyer" les zones de saisies
     */
    private void emptyInputEditText(){

        textInputEditTextQuestion.setText(null);
    }
}
