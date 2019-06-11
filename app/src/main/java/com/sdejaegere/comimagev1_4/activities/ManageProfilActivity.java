package com.sdejaegere.comimagev1_4.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.sdejaegere.comimagev1_4.R;
import com.sdejaegere.comimagev1_4.helpers.InputValidation;
import com.sdejaegere.comimagev1_4.model.User;
import com.sdejaegere.comimagev1_4.sql.DatabaseHelper;

public class ManageProfilActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = ManageProfilActivity.this;

    private TextInputLayout textInputLayoutModifyName;
    private TextInputLayout textInputLayoutModifyEmail;
    private TextInputLayout textInputLayoutModifyPassword;
    private TextInputLayout textInputLayoutModifyConfirmPassword;

    private TextInputEditText textInputEditTextModifyName;
    private TextInputEditText textInputEditTextModifyEmail;
    private TextInputEditText textInputEditTextModifyPassword;
    private TextInputEditText textInputEditTextModifyConfirmPassword;

    private AppCompatButton appCompatButtonSaveModifiedProfil;
    private AppCompatButton appCompatButtonCancelModifyProfil;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    private String nameFromIntent;
    private String emailFromIntent;
    private String idFromIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_manage_profil);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {

        textInputLayoutModifyName = findViewById(R.id.textInputLayoutModifyName);
        textInputLayoutModifyEmail = findViewById(R.id.textInputLayoutModifyEmail);
        textInputLayoutModifyPassword = findViewById(R.id.textInputLayoutModifyPassword);
        textInputLayoutModifyConfirmPassword = findViewById(R.id.textInputLayoutModifyConfirmPassword);

        textInputEditTextModifyName = findViewById(R.id.textInputEditTextModifyName);
        textInputEditTextModifyEmail = findViewById(R.id.textInputEditTextModifyEmail);
        textInputEditTextModifyPassword = findViewById(R.id.textInputEditTextModifyPassword);
        textInputEditTextModifyConfirmPassword = findViewById(R.id.textInputEditTextModifyConfirmPassword);

        appCompatButtonSaveModifiedProfil =  findViewById(R.id.appCompatButtonSaveModifiedProfil);
        appCompatButtonCancelModifyProfil =  findViewById(R.id.appCompatButtonCancelModifyProfil);

    }

    private void initListeners() {
        appCompatButtonSaveModifiedProfil.setOnClickListener(this);
        appCompatButtonCancelModifyProfil.setOnClickListener(this);

    }

    private void initObjects() {
        idFromIntent = getIntent().getStringExtra("IDUSER");
        emailFromIntent = getIntent().getStringExtra("EMAIL");
        nameFromIntent = getIntent().getStringExtra("NAME");
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
        textInputEditTextModifyName.setText(nameFromIntent);
        textInputEditTextModifyEmail.setText(emailFromIntent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonSaveModifiedProfil:
                postDataToSQLite();
                break;

            case R.id.appCompatButtonCancelModifyProfil:
                onBackPressed();
                break;

        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextModifyName, textInputLayoutModifyName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextModifyEmail, textInputLayoutModifyEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextModifyEmail, textInputLayoutModifyEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextModifyPassword, textInputLayoutModifyPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextModifyPassword, textInputEditTextModifyConfirmPassword,
                textInputLayoutModifyConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }



            user.setName(textInputEditTextModifyName.getText().toString().trim());
            user.setEmail(textInputEditTextModifyEmail.getText().toString().trim());
            user.setPassword(textInputEditTextModifyPassword.getText().toString().trim());
            user.setId(Integer.parseInt(idFromIntent));

            databaseHelper.updateUser(user);
            hideKeyboard(activity);

            Intent accountsIntent = new Intent(activity, LoginActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextModifyEmail.getText().toString().trim());
            accountsIntent.putExtra("PASSWORD", textInputEditTextModifyPassword.getText().toString().trim());
            startActivity(accountsIntent);

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
