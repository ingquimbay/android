package com.carlitos.mapamundi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_EMAIL = "EmailLogin";
    private FirebaseAuth mAuth;

    private static final int REQUEST_SIGNUP = 0;
    EditText email, password;
    Button loginButton;
    TextView singupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);

        loginButton = (Button) findViewById(R.id.btn_login);
        singupLink = (TextView) findViewById(R.id.link_signup);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().length() == 0 || password.getText().length() == 0){
                    Snackbar.make(v, "El email y/o password están vacios", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    signInEmail(email.getText().toString(), password.getText().toString());
                }
            }
        });

        singupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    private void signInEmail(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG_EMAIL, "SignInWithEmail : success");
                            Intent i = new Intent(getBaseContext(), MenuActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Log.w(TAG_EMAIL, "SignInWithEmail : failure",task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
