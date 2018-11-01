package carlitos.fibonacci.com.layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button internet = (Button) findViewById(R.id.web_button);
        Button frame = (Button) findViewById(R.id.frame_button);
        Button lista = (Button) findViewById(R.id.button_lista);

        EditText username = (EditText) findViewById(R.id.user_name_text);
        String nombre = username.getText().toString();

        Spinner spinnerText = (Spinner) findViewById(R.id.spinner_list);
        String educacion = spinnerText.getSelectedItem().toString();

        internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Main2Activity.class);
                startActivity(intent);
            }
        });

        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Main3Activity.class);

                startActivity(intent);
            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Main4Activity.class);
                startActivity(intent);
            }
        });
    }
}
