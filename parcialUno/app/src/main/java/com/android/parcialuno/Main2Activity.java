package com.android.parcialuno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {

    private ListView listV;
    private Double num, aux, res1 = Double.valueOf(1), res2 = Double.valueOf(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listV = (ListView) findViewById(R.id.listNumbers);
        num = getIntent().getDoubleExtra("number", 1);
        ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(getBaseContext(), android.R.layout.simple_list_item_1);
        listV.setAdapter(adapter);
        adapter.add(res1);
        adapter.add(res2);

        for (int i = 0; i < num; i ++){
            aux = res1 * res2;
            res1 = res2;
            res2 = aux;
            adapter.add(aux);
        }
    }
}
