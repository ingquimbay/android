package carlitos.fibonacci.com.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main4Activity extends AppCompatActivity {

    String[] arregloPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        initArray();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arregloPaises);
        ListView listView = (ListView) findViewById(R.id.view_de_lista);
        listView.setAdapter(adapter);
    }

    private void initArray(){
        arregloPaises = new String[10];
        arregloPaises = new String[]{"Argentina","Brasil","Bolivia","Chile","Colombia","Ecuador","Paraguay","Uruguay","Venezuela"};
    }
}
