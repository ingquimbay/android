package com.grupo.elementoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listener image button
        findViewById(R.id.perfil_imgBttn).setOnClickListener(this);
        findViewById(R.id.residencia_imgBttn).setOnClickListener(this);
        findViewById(R.id.ocupacion_imgBttn).setOnClickListener(this);
        findViewById(R.id.ingresos_imgBttn).setOnClickListener(this);
        //listener text clickable
        findViewById(R.id.perfil_txtvw).setOnClickListener(this);
        findViewById(R.id.residencia_txtvw).setOnClickListener(this);
        findViewById(R.id.ocupacion_txtvw).setOnClickListener(this);
        findViewById(R.id.ingresos_txtvw).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.perfil_imgBttn || id == R.id.perfil_txtvw){
            Intent i = new Intent(getBaseContext(), PerfilUsuarioActivity.class);
            startActivity(i);
        }
        if (id == R.id.residencia_imgBttn || id == R.id.residencia_txtvw){
            Intent i = new Intent(getBaseContext(), ResidenciaActivity.class);
            startActivity(i);
        }
        if (id == R.id.ocupacion_imgBttn || id == R.id.ocupacion_txtvw){
            Intent i = new Intent(getBaseContext(), OcupacionActivity.class);
            startActivity(i);
        }
        if (id == R.id.ingresos_imgBttn || id == R.id.ingresos_txtvw){
            Intent i = new Intent(getBaseContext(), IngresosActivity.class);
            startActivity(i);
        }
    }
}
