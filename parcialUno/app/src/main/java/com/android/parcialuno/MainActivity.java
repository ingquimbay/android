package com.android.parcialuno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText numText;
    private Button okButton;
    private Double number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numText = (EditText) findViewById(R.id.editText);
        okButton = (Button) findViewById(R.id.button);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = Double.parseDouble(numText.getText().toString());

                if (number > 0 && number < 21){
                    Intent i = new Intent(getBaseContext(), Main2Activity.class);
                    i.putExtra("number", number);
                    startActivity(i);
                    numText.getText().clear();
                }
                else {
                    Toast.makeText(getBaseContext(), "Number not valid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
