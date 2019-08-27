package quimbay.com.adivinaypaises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class PaisesActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> paisesLista = new ArrayList<>();
    String nombresPaises [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paises);

        String jsonStr = loadJSONfromAsset();
        if (jsonStr != null){
            try {
                JSONObject jsono = new JSONObject(loadJSONfromAsset());
                JSONArray paisesJArray = jsono.getJSONArray("paises");
                nombresPaises = new String[paisesJArray.length()];

                for (int i = 0; i < paisesJArray.length(); i++){
                    JSONObject jsonp = paisesJArray.getJSONObject(i);

                    String capital = jsonp.getString("capital");
                    String nombre_pais = jsonp.getString("nombre_pais");
                    String nombre_pais_int = jsonp.getString("nombre_pais_int");
                    String sigla = jsonp.getString("sigla");

                    HashMap<String, String> paisTmp = new HashMap<>();

                    paisTmp.put("capital",capital);
                    paisTmp.put("nombre_pais",nombre_pais);
                    paisTmp.put("nombre_pais_int",nombre_pais_int);
                    paisTmp.put("sigla",sigla);

                    paisesLista.add(paisTmp);
                    nombresPaises[i] = nombre_pais;
                }
            } catch (final JSONException jex){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Json parsing error: "
                         + jex.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,nombresPaises);

        ListView lv = (ListView) findViewById(R.id.countriesLstVw);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(),PaisDetalleActivity.class);
                HashMap<String,String> paisHM = paisesLista.get(i);
                intent.putExtra("paisHM",paisHM);
                startActivity(intent);
            }
        });

    }

    private String loadJSONfromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("paises.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
