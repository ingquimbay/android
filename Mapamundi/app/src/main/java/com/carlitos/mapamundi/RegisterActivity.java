package com.carlitos.mapamundi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG_SIGNIN = "EMAIL_SIGNIN";
    EditText firstName, lastName, email, password;
    Button registerButton;
    TextView loginLink;
    private ProgressDialog progDialog;
    private FirebaseAuth mAuth;
    private FirebaseUser fbUser;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = (EditText) findViewById(R.id.input_firstname);
        lastName = (EditText) findViewById(R.id.input_lastname);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);

        registerButton = (Button) findViewById(R.id.btn_signup);
        loginLink = (TextView) findViewById(R.id.link_login);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        progDialog = new ProgressDialog(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void createAccount(){

        if (firstName.getText().toString().isEmpty()){
            firstName.setError("First name is empty");
            return;
        }
        if (lastName.getText().toString().isEmpty()){
            lastName.setError("Last name is empty");
            return;
        }
        if (!isValidEmail(email.getText().toString())){
            email.setError("Email is invalid");
            return;
        }
        if (password.getText().toString().isEmpty()){
            password.setError("Password is empty");
            return;
        }

        progDialog.setMessage("Signing In ...");
        progDialog.show();

        mAuth.createUserWithEmailAndPassword(email.getText().toString(),
                password.getText().toString()).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            addUserFirebase();
                            Log.d(TAG_SIGNIN, "createUserWithEmail : success");
                            Intent i = new Intent(getBaseContext(), MenuActivity.class);
                            startActivity(i);
                        } else {
                            Log.w(TAG_SIGNIN, "createUserWithEmail : failure");
                            Toast.makeText(getBaseContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } progDialog.dismiss();
                    }
                });
    }

    private void addUserFirebase() {
        UserInfo newUser = new UserInfo(firstName.getText().toString(),
                lastName.getText().toString());
        fbUser = mAuth.getCurrentUser();
        dbRef.child(fbUser.getUid()).setValue(newUser);
    }

    private final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
