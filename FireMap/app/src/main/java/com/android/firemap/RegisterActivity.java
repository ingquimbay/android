package com.android.firemap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstnameUsr, lastnameUsr, emailUsr, psswdUsr;
    private String firstname, lastname, email, password;
    private Button signupButton;
    private FirebaseAuth mAuth;
    private FirebaseUser userFb;
    private DatabaseReference dbRef;
    private ProgressDialog progDialog;
    private userInfo userI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signupButton = (Button) findViewById(R.id.buttonRegistrarse);
        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        progDialog = new ProgressDialog(this);
        initiateEditText();
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        firstname = firstnameUsr.getText().toString().trim();
        lastname = lastnameUsr.getText().toString().trim();
        email = emailUsr.getText().toString().trim();
        password = psswdUsr.getText().toString().trim();
        if (isFirstNameValid(firstname) && isLastNameValid(lastname) &&
                isEmailValid(email) && isPasswordValid(password)) {
            progDialog.setMessage("Registrando usuario...");
            progDialog.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
                    new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        saveInformation();
                        Toast.makeText(getBaseContext(), R.string.regist_success_txt,
                                Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getBaseContext(), WelcomeActivity.class));
                    } else {
                        Toast.makeText(getBaseContext(), R.string.regist_failed_txt,
                                Toast.LENGTH_LONG).show();
                    } progDialog.dismiss();
                    firstnameUsr.getText().clear();
                    lastnameUsr.getText().clear();
                    emailUsr.getText().clear();
                    psswdUsr.getText().clear();
                }
            });
        }
    }

    private void saveInformation() {
        firstname = firstnameUsr.getText().toString().trim();
        lastname = lastnameUsr.getText().toString().trim();
        userInfo userTemp = new userInfo(firstname, lastname);
        userFb = mAuth.getCurrentUser();
        dbRef.child(userFb.getUid()).setValue(userTemp);
    }

    private boolean isFirstNameValid(String firstname) {
        return !firstname.isEmpty();
    }

    private boolean isLastNameValid(String lastname) {
        return !lastname.isEmpty();
    }

    private boolean isEmailValid(String email) {
        if (email == null) { return false;
        } else { return Patterns.EMAIL_ADDRESS.matcher(email).matches(); }
    }

    private boolean isPasswordValid(String password) { return password.length() > 8; }

    private void initiateEditText() {
        firstnameUsr = (EditText) findViewById(R.id.nombreET);
        lastnameUsr = (EditText) findViewById(R.id.apellidoET);
        emailUsr = (EditText) findViewById(R.id.correoET);
        psswdUsr = (EditText) findViewById(R.id.passwordET);
    }
}
