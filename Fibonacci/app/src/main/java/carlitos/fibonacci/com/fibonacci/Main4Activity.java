package carlitos.fibonacci.com.fibonacci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Main4Activity extends AppCompatActivity {


    ArrayList<HashMap<String, String>> paisesLista = new ArrayList<>();

    String nombresPaises [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        String jsonStr = loadJSONfromAsset();
        if (jsonStr != null){
            try {
                JSONObject json = new JSONObject(loadJSONfromAsset());
                JSONArray paisesJSONArray = json.getJSONArray("paises");
                nombresPaises = new String[paisesJSONArray.length()];

                for (int i = 0; i < paisesJSONArray.length(); i++){
                    JSONObject jsonP = paisesJSONArray.getJSONObject(i);

                    String capital = jsonP.getString("capital");
                    String nombre_pais = jsonP.getString("nombre_pais");
                    String nombre_pais_int = jsonP.getString("nombre_pais_int");
                    String sigla = jsonP.getString("sigla");

                    HashMap<String, String> paisTmp = new HashMap<>();

                    paisTmp.put("capital",capital);
                    paisTmp.put("nombre_pais",nombre_pais);
                    paisTmp.put("nombre_pais_int",nombre_pais_int);
                    paisTmp.put("sigla",sigla);

                    paisesLista.add(paisTmp);

                    nombresPaises[i] = nombre_pais;

                }
            } catch (final JSONException e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresPaises);

        //ListAdapter adapter = new SimpleAdapter(this, paisesLista, R.layout.activity_main5, new String[]{"nombre_pais"}, new int[]{R.id.nombrePaisTxtVw});
        ListView lv = (ListView) findViewById(R.id.listaPaisesView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(),Main5Activity.class);
                HashMap<String,String> paisHM = paisesLista.get(i);
                intent.putExtra("paisHM",paisHM);
                startActivity(intent);
            }
        });

    }

    public String loadJSONfromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("paises.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex){
            ex.printStackTrace();
            return null;

        }

        return json;
    }
}
