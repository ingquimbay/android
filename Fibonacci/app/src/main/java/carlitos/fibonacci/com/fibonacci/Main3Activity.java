package carlitos.fibonacci.com.fibonacci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        int factor = getIntent().getIntExtra("fac",0);
        TextView textv = (TextView) findViewById(R.id.textView3);

        int res = factorial(factor);
        textv.setText("Factorial de " + factor + " es " + res + "\n");

        textv.append("Multiplicacion: ");
        for (int i = 1; i < factor; i++){
            textv.append(i + " * ");
        }
        textv.append("" + factor);
    }

    public int factorial(int n){
        int x, fac = 1;

        for (x = 1; x <= n; x++){
            fac = fac * x;
        }
        return fac;
    }
}
