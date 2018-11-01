package quimbay.com.tallercm;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class GuessActivity extends AppCompatActivity {

    EditText inputNum;
    TextView hintText, triesText, activityTitle;
    Button okButton, rangeButton;
    int numero, numEntrada, intentos = 0, maxValor = 50;
    private static final int RANGE_ACTIVITY_REQUEST_CODE = 0;
    private static String [] mProjection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        inputNum = (EditText) findViewById(R.id.inputNumber);
        hintText = (TextView) findViewById(R.id.hintTxtvw);
        triesText = (TextView) findViewById(R.id.triesTxtvw);
        activityTitle = (TextView) findViewById(R.id.appTitle);
        okButton = (Button) findViewById(R.id.okButton);
        rangeButton = (Button) findViewById(R.id.newRangebutton);

        numero = numeroAleatorio(maxValor);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numEntrada = Integer.parseInt(inputNum.getText().toString());
                if (validarInput(numEntrada, maxValor)){
                    intentos++;
                    if (numEntrada == numero){
                        hintText.setText("Correcto has adivinado el numero :)");
                        Toast.makeText(view.getContext(),"Correcto has adivinado el numero :)",Toast.LENGTH_LONG).show();
                    } else {
                        if (numEntrada < numero){
                            hintText.setText("Tu numero es menor");
                        } if (numEntrada > numero){
                            hintText.setText("Tu numero es mayor");
                        }
                    } triesText.setText("Numero de intentos " + intentos);
                } else {
                    Toast.makeText(view.getContext(),"Numero no valido!",Toast.LENGTH_LONG).show();
                } inputNum.getText().clear();
            }
        });

        rangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), RangeActivity.class);
                startActivityForResult(i,RANGE_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RANGE_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                maxValor = data.getIntExtra("newRange",50);
                intentos = 0;
                numero = numeroAleatorio(maxValor);
                activityTitle.setText("Adivina un numero entre 0 y " + maxValor + "!");
            }
        }
    }

    public static int numeroAleatorio(int maxValor){
        Random r = new Random();
        return r.nextInt(maxValor);
    }

    public boolean validarInput(int inputNum, int maxValor){
        if (inputNum >= 0 && inputNum <= maxValor) return true;
        return false;
    }
}
