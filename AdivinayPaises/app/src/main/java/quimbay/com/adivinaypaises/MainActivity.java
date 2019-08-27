package quimbay.com.adivinaypaises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputNumber;
    int numberRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton guessButton = (ImageButton) findViewById(R.id.adivinaButton);
        ImageButton countriesButton = (ImageButton) findViewById(R.id.paisesButton);
        inputNumber = (EditText) findViewById(R.id.inputNumber);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inputNumber.getText().toString().isEmpty()) {
                    numberRange = Integer.parseInt(inputNumber.getText().toString());
                    Intent i = new Intent(view.getContext(), AdivinaActivity.class);
                    i.putExtra("range", numberRange);
                    setResult(RESULT_OK,i);
                    startActivity(i);
                } else {
                    Toast.makeText(view.getContext(),"Debe digitar un numero.",
                            Toast.LENGTH_LONG).show();
                } inputNumber.getText().clear();
            }
        });

        countriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PaisesActivity.class);
                startActivity(i);
            }
        });
    }
}
