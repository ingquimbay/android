package quimbay.com.adivinaypaises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class AdivinaActivity extends AppCompatActivity {

    EditText inputNumber;
    TextView activityTitle, hintText, triesText;
    Button okButton;
    int num, inNumber, tries, maxValue;
    private static final int RANGE_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivina);

        inputNumber = (EditText) findViewById(R.id.inputNumberEditTxt);
        activityTitle = (TextView) findViewById(R.id.adivinaTxtVw);
        hintText = (TextView) findViewById(R.id.hintTextView);
        triesText = (TextView) findViewById(R.id.triesTextView);
        okButton = (Button) findViewById(R.id.verifyButton);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            maxValue = b.getInt("range");
            tries = 0;
            num = randomNumber(maxValue);
            activityTitle.setText("Adivina un numero entre 0 y " + maxValue + "!");
        }

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inNumber = Integer.parseInt(inputNumber.getText().toString());
                if (validateInput(inNumber,maxValue)){
                    tries++;
                    if (inNumber == num) {
                        hintText.setText("Correcto has adivinado el numero :)");
                    } else {
                        if (inNumber < num) {
                            hintText.setText("Tu numero es menor");
                        } if (inNumber > num) {
                            hintText.setText("Tu numero es mayor");
                        }
                    } triesText.setText("Numero de intentos " + tries);
                } else {
                    Toast.makeText(view.getContext(),"Numero no valido!",Toast.LENGTH_LONG).show();
                } inputNumber.getText().clear();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RANGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                maxValue = data.getIntExtra("range",50);
                tries = 0;
                num = randomNumber(maxValue);
                activityTitle.setText("Adivina un numero entre 0 y " + maxValue + "!");
            }
        }
    }

    private int randomNumber(int maxValue) {
        Random r = new Random();
        return r.nextInt(maxValue);
    }

    private boolean validateInput(int inNumber, int maxValue) {
        if (inNumber >= 0 && inNumber <= maxValue) return true;
        return false;
    }
}
