package com.sdejaegere.comimagev1_4.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.helpers.InputValidation;
import com.sdejaegere.comimagev1_4.sql.DatabaseHelper;

import java.io.UnsupportedEncodingException;

/**
 * Créé par sdejaegere le 19/05/2019
 * Cette activité correspond au login de l'appli
 * On peut depuis soit se logguer et accéder au menu (MenuActivity)
 * Soit s'enregister (RegisterActivity)
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private String emailFromIntent;
    private String passwordFromIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cette ligne de code cache la barre d'info d'android (heure, batterie etc), permettant d'avoir
        //une appli en plein écran. Doit être utilisé avant l'appel au layout
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        //on cache la barre haute présente par défaut
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

        //On vérifie si l'activité possède un intent "email". Si c'est le cas, ça veut dire que l'on est arrivé ici
        //depuis RegisterActivity. On ajoutera donc l'email et le mdp du nouvel utilisateur dans les champs correspondants
        //afin d'éviter à l'utilisateur de devoir entrer à nouveau ses informations
        if(getIntent().hasExtra("EMAIL")){

            emailFromIntent = getIntent().getStringExtra("EMAIL");
            passwordFromIntent = getIntent().getStringExtra("PASSWORD");

            textInputEditTextEmail.setText(emailFromIntent);
            textInputEditTextPassword.setText(passwordFromIntent);
        }
    }

    /**
     * Méthode pour initialiser les vues
     */
    private void initViews() {

        nestedScrollView = findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = findViewById(R.id.textViewLinkRegister);

    }

    /**
     * Méthode pour initialiser les listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * Méthode pour initialiser les objets nécessaires
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * Méthode pour le listener onCLick
     * Soit bouton login, on appelle la méthode verifyFromSQLite
     * soit textlink, on active l'intent direction RegistryActivity
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                hideKeyboard(activity);
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Aller à RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * Méthode utilisée pour valider les saisies utilisateur grâce à l'objet InputValidation et vérifier la validité du login depuis SQLite
     */
    private void verifyFromSQLite() {
        //vérifier que des données soient saisies
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        //vérifier que le champs email soit bien un email
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        //vérifier que des données soient bien saisies
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }

        //si on trouve une correspondance, on passe à l'activité MenuActivity
        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , MD5(textInputEditTextPassword.getText().toString().trim()))) {

            Intent accountsIntent = new Intent(activity, MenuActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);

        //sinon
        } else {

            //snackbar (chez Léon...) pour montrer que l'enregistrement n'est pas passé
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * Méthode pour effacer les zones de saisie
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

    /**
     *méthode pour cacher le clavier virtuel
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
     * méthode pour hasher le mdp en MD5
     * @param md5
     * @return
     */
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }
}