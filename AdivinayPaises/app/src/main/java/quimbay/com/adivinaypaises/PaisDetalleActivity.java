package quimbay.com.adivinaypaises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class PaisDetalleActivity extends AppCompatActivity {

    public static HashMap<String,String> parameter = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais_detalle);

        TextView countryTV = (TextView) findViewById(R.id.countryTitleTxtVw);
        TextView capitalTV = (TextView) findViewById(R.id.capitalNameTxtVw);
        TextView intCountryTV = (TextView) findViewById(R.id.intCountryNameTxtVw);
        TextView initialTV = (TextView) findViewById(R.id.initialsCountryTxtVw);

        Intent i = getIntent();
        parameter = (HashMap<String, String>) i.getSerializableExtra("paisHM");

        String country = parameter.get("nombre_pais");
        String capital = parameter.get("capital");
        String intName = parameter.get("nombre_pais_int");
        String initial = parameter.get("sigla");

        countryTV.setText(country);
        capitalTV.setText(capital);
        intCountryTV.setText(intName);
        initialTV.setText(initial);
    }
}
