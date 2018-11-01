package com.example.carlitos.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView firstTextView = (TextView) findViewById(R.id.textView);
        final EditText editTxt = (EditText) findViewById(R.id.editText);
        Button firstButton = (Button) findViewById(R.id.firstbutton);

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstTextView.setText("You clicked me");
                Intent intent = new Intent(view.getContext(), Main2Activity.class);
                intent.putExtra("name", editTxt.getText().toString());
                startActivity(intent);

            }
        });
    }
}
