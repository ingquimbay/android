package com.grupo.elementoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ResidenciaActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residencia);

        //image button listeners
        findViewById(R.id.acceptButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);
        //dropdown lists
        Spinner trSpinner = (Spinner) findViewById(R.id.tipoResidenciaSpinner);
        Spinner estSpinner = (Spinner) findViewById(R.id.estratoSpinner);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.tipoResidenciaArray));
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trSpinner.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.estratoArray));
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estSpinner.setAdapter(arrayAdapter2);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cancelButton){
            this.finish();
        }
        if (id == R.id.acceptButton){

        }
    }
}
