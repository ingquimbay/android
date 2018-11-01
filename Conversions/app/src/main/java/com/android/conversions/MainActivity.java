package com.android.conversions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner unitSpnr;
    private EditText numberEdTxt;
    TextView tspTV, cupsTV, tbspTV, ozTV, kgTV, lbsTV, galTV, qrtTV, ltTV, mlTV, gmTV, pintTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemstoSpinner();

        addListenertoSpinner();

        numberEdTxt = (EditText) findViewById(R.id.convNumET);

        initializeTextViews();
    }

    private void addListenertoSpinner() {

        unitSpnr = (Spinner) findViewById(R.id.units_spinner);
        unitSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemSelected = adapterView.getItemAtPosition(i).toString();
                convertingFrom(itemSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(getBaseContext(),"Unit not selected",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void convertingFrom(String currentUnit) {

        if (currentUnit.equals("teaspoon")){
            updateUnitTypeTsp(Conversion.Unit.tsp);
        } else {
            if (currentUnit.equals("tablespoon")){
                updateUnitTypeOther(Conversion.Unit.tbs);
            } else if (currentUnit.equals("cups")){
                updateUnitTypeOther(Conversion.Unit.cup);
            } else if (currentUnit.equals("ounces")){
                updateUnitTypeOther(Conversion.Unit.oz);
            } else if (currentUnit.equals("kilograms")){
                updateUnitTypeOther(Conversion.Unit.kg);
            } else if (currentUnit.equals("pounds")){
                updateUnitTypeOther(Conversion.Unit.lbs);
            } else if (currentUnit.equals("gallons")){
                updateUnitTypeOther(Conversion.Unit.gallon);
            } else if (currentUnit.equals("quarters")){
                updateUnitTypeOther(Conversion.Unit.quart);
            } else if (currentUnit.equals("litres")){
                updateUnitTypeOther(Conversion.Unit.liter);
            } else if (currentUnit.equals("millilitres")){
                updateUnitTypeOther(Conversion.Unit.ml);
            } else if (currentUnit.equals("grams")){
                updateUnitTypeOther(Conversion.Unit.gm);
            } else if (currentUnit.equals("pints")){
                updateUnitTypeOther(Conversion.Unit.pint);
            }
        }
    }

    private void updateUnitTypeTsp(Conversion.Unit currenUnit) {

        double numToConvert = Double.parseDouble(numberEdTxt.getText().toString());
        //Conversion qty = new Conversion(numToConvert, currenUnit);
        String tspValue = numToConvert + " tsp";
        tspTV.setText(tspValue);

        updateUnitTextTsp(numToConvert, Conversion.Unit.tbs, tbspTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.cup, cupsTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.oz, ozTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.kg, kgTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.lbs, lbsTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.gallon, galTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.quart, qrtTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.liter, ltTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.ml, mlTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.gm, gmTV);
        updateUnitTextTsp(numToConvert, Conversion.Unit.pint, pintTV);
    }

    private void updateUnitTextTsp(double numToConvert, Conversion.Unit unit, TextView txtV) {

        Conversion qty = new Conversion(numToConvert, Conversion.Unit.tsp);
        String tmpUnit = qty.to(unit).toString();
        txtV.setText(tmpUnit);
    }

    private void updateUnitTypeOther(Conversion.Unit currenUnit) {

        double numToConvert = Double.parseDouble(numberEdTxt.getText().toString());
        Conversion qty = new Conversion(numToConvert, currenUnit);
        String valueTsp = qty.to(Conversion.Unit.tsp).toString();
        tspTV.setText(valueTsp);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.tbs, tbspTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.cup, cupsTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.oz, ozTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.kg, kgTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.lbs, lbsTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.gallon, galTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.quart, qrtTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.liter, ltTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.ml, mlTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.gm, gmTV);
        updateUnitTextTsp(numToConvert, currenUnit, Conversion.Unit.pint, pintTV);

        if (currenUnit.name().equals(qty.unit.name())){

            String currentUnitTV = numToConvert + " " + qty.unit.name();
            String currentTextView = qty.unit.name() + "TV";
            int tempID = getResources().getIdentifier(currentTextView, "id",
                    MainActivity.this.getPackageName());
            TextView tmpTV = (TextView) findViewById(tempID);
            tmpTV.setText(currentUnitTV);
        }
    }

    private void updateUnitTextTsp(double numToConvert, Conversion.Unit currenUnit,
                                   Conversion.Unit preferredUnit, TextView targetTV) {

        Conversion qty = new Conversion(numToConvert, currenUnit);
        String tempTV = qty.to(Conversion.Unit.tsp).to(preferredUnit).toString();
        targetTV.setText(tempTV);
    }

    private void addItemstoSpinner() {

        unitSpnr = (Spinner) findViewById(R.id.units_spinner);

        ArrayAdapter<CharSequence> unitSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.unit_types, android.R.layout.simple_spinner_item);
        unitSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpnr.setAdapter(unitSpinnerAdapter);
    }

    private void initializeTextViews() {

        tspTV = (TextView) findViewById(R.id.tspTV);
        cupsTV = (TextView) findViewById(R.id.cupTV);
        tbspTV = (TextView) findViewById(R.id.tbsTV);
        ozTV = (TextView) findViewById(R.id.ozTV);
        kgTV = (TextView) findViewById(R.id.kgTV);
        lbsTV = (TextView) findViewById(R.id.lbs);
        galTV = (TextView) findViewById(R.id.gallonTV);
        qrtTV = (TextView) findViewById(R.id.quartTV);
        ltTV = (TextView) findViewById(R.id.literTV);
        mlTV = (TextView) findViewById(R.id.mlTV);
        gmTV = (TextView) findViewById(R.id.gmTV);
        pintTV = (TextView) findViewById(R.id.pintTV);
    }
}
