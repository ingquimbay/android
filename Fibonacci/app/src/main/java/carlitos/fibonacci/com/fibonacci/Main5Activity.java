package carlitos.fibonacci.com.fibonacci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class Main5Activity extends AppCompatActivity {

    public static HashMap<String, String> parameters = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        TextView paisTW = (TextView) findViewById(R.id.nombrePaisTxtVw);
        TextView capitalTW = (TextView) findViewById(R.id.capitalTxtVw);
        TextView siglaTW = (TextView) findViewById(R.id.siglaTxtVw);

        Intent intent = getIntent();
        parameters = (HashMap<String,String>) intent.getSerializableExtra("paisHM");

        String pais = parameters.get("nombre_pais");
        String capital = parameters.get("capital");
        String sigla = parameters.get("sigla");

        paisTW.setText(pais);
        capitalTW.setText(capital);
        siglaTW.setText(sigla);
    }
}
