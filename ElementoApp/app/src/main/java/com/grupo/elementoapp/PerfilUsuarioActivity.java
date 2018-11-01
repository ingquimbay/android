package com.grupo.elementoapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

public class PerfilUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    //TextView fechaNacimiento = (TextView) findViewById(R.id.fechaNacimiento_txtvw);
    private int mAnio, mMes, mDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        //image button listeners
        findViewById(R.id.acceptButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);
        findViewById(R.id.calendarButton).setOnClickListener(this);
        //dropdown lists
        Spinner ecSpinner = (Spinner) findViewById(R.id.estadoCivilSpinner);
        Spinner tdSpinner = (Spinner) findViewById(R.id.tipoDocumentoSpinner);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.estadoCivilArray));
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ecSpinner.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.tipoDocumentoArray));
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tdSpinner.setAdapter(arrayAdapter2);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cancelButton){
            this.finish();
        }
        if (id == R.id.acceptButton){

        }
        if (id == R.id.calendarButton){
            final Calendar c = Calendar.getInstance();
            mAnio = c.get(Calendar.YEAR);
            mMes = c.get(Calendar.MONTH);
            mDia = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int anio, int mes, int dia) {
                   // fechaNacimiento.setText(dia + " / " + mes + " / " + anio);
                }
            }, mAnio, mMes, mDia);
            datePickerDialog.show();
        }
    }
}
