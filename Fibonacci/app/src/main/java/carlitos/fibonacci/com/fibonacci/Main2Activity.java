package carlitos.fibonacci.com.fibonacci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Long intent = getIntent().getLongExtra("fib",0);
        Long n;
        TextView result = new TextView(this);
        LinearLayout linear = (LinearLayout) findViewById(R.id.linear2);
        result.setText("Fibonacci de " + intent.toString() + " es\n");

        for (long i = 0; i < intent; i++){
            n = fibonacci(i);
            result.append(n.toString()+"\n");
        }

        linear.addView(result);

    }

    public long fibonacci(long n)  {
        if(n == 0)
            return 0;
        else if(n == 1)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
