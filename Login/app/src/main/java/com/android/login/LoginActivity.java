package com.android.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText emailTxt, psswdTxt;
    private Button signinButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeEditText();
        initializeButton();

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()){
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailTxt.getText().toString();
        if (TextUtils.isEmpty(email)){
            emailTxt.setError("email is required");
            valid = false;
        } else {
            emailTxt.setError(null);
        }

        String password = psswdTxt.getText().toString();
        if (TextUtils.isEmpty(email)){
            psswdTxt.setError("password is required");
            valid = false;
        } else {
            psswdTxt.setError(null);
        }

        return valid;
    }

    private void initializeEditText() {

        emailTxt = (EditText) findViewById(R.id.emailET);
        psswdTxt = (EditText) findViewById(R.id.psswdET);
    }

    private void initializeButton() {

        signinButton = (Button) findViewById(R.id.signinButton);
        signupButton = (Button) findViewById(R.id.signupButton);
    }
}
